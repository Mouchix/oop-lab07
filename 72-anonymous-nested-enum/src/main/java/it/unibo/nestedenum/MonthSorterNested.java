package it.unibo.nestedenum;

import java.util.Comparator;
import java.util.Locale;
import java.util.Objects;

/**
 * Implementation of {@link MonthSorter}.
 */
public final class MonthSorterNested implements MonthSorter {

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

        public Month fromString(String month){
            month.toLowerCase();
            Month result;
            for(Month m: Month.values()){
                if(m.getActualName().equals(month)){
                    result = m;
                }
            }
            return result;
        }
    }


    @Override
    public Comparator<String> sortByDays() {
        return null;
    }

    @Override
    public Comparator<String> sortByOrder() {
        return null;
    }
}
