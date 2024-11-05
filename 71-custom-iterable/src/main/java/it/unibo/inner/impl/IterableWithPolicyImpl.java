package it.unibo.inner.impl;

import java.lang.reflect.Array;
import java.util.Iterator;
import java.util.List;

import it.unibo.inner.api.IterableWithPolicy;
import it.unibo.inner.api.Predicate;

public class IterableWithPolicyImpl<T> implements IterableWithPolicy<T>{

    final private List<T> elem;
    private Predicate<T> pre;

    public IterableWithPolicyImpl(final T[] arr){
        this(arr, new Predicate<T>() {
            @Override
            public boolean test(T elem) {
                return true;
            }
        });
    }

    public IterableWithPolicyImpl(final T[] arr, final Predicate<T> p){
        this.elem = List.of(arr);
        this.pre = p;
    }

    @Override
    public Iterator<T> iterator() {
        return this.new InnerClass();
    }

    @Override
    public void setIterationPolicy(final Predicate<T> filter) {
        this.pre = filter;
    }

    public class InnerClass implements Iterator<T>{

        private int index = 0;

        @Override
        public boolean hasNext() {
            return (index + 1) <= elem.size();
        }

        @Override
        public T next() {
            return elem.get(index++);   
        }
    }
}

