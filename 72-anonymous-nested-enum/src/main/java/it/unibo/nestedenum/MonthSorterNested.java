package it.unibo.nestedenum;

import java.util.Comparator;
import java.util.Objects;

/**
 * Implementation of {@link MonthSorter}.
 */
public final class MonthSorterNested implements MonthSorter {

    private static final Comparator<String> BY_DAYS = new SortByDate();
    private static final Comparator<String> BY_ORDER = new SortByMonthOrder();

    public enum Month{
        GENNAIO("Gennaio",31),
        FEBBRAIO("Febbraio", 28),
        MARZO("Marzo", 31),
        APRILE("Aprile", 30),
        MAGGIO("Maggio", 31),
        GIUGNO("Giugno", 30),
        LUGLIO("Luglio", 31),
        AGOSTO("Agosto", 31),
        SETTEMBRE("Settembre",30),
        OTTOBRE("Ottobre", 31),
        NOVEMBRE("Novembre", 30),
        DICEMBRE("Dicembre", 31);

        private final int nDays;
        private final String actualName;

        public int getnDays() {
            return this.nDays;
        }

        public String getActualName() {
            return this.actualName;
        }

        private Month(final String name, final int days){
            this.actualName = name;
            this.nDays = days;
        }

        public static Month fromString(String month){
            Objects.requireNonNull(month);

            try {
                return valueOf(month);
            } catch (IllegalArgumentException ex) {
                Month result = null;
                for(Month m: values()){
                    if(m.actualName.toLowerCase().startsWith(month.toLowerCase())){
                        if(result != null){
                            throw new IllegalArgumentException("Ambigous search for the string: " + month, ex);
                        }

                        result = m;
                    }
                }

                if(result == null) {
                    throw new IllegalArgumentException("No match found for value: " + month, ex);
                }
                
                return result;
            }
        }
    }


    @Override
    public Comparator<String> sortByDays() {
        return BY_DAYS;
    }

    @Override
    public Comparator<String> sortByOrder() {
        return BY_ORDER;
    }

    private static class SortByMonthOrder implements Comparator<String>{

        @Override
        public int compare(String o1, String o2) {
            final Month m1 = Month.fromString(o1);
            final Month m2 = Month.fromString(o2);

            return m1.compareTo(m2);

        }

    }

    private static class SortByDate implements Comparator<String>{

        @Override
        public int compare(String o1, String o2) {
            final Month m1 = Month.fromString(o1);
            final Month m2 = Month.fromString(o2);

            return Integer.compare(m1.getnDays(), m2.getnDays());
        }

    }
}
