package ehist.mem.date;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * === DateManager Class ===
 * </p><p>
 * Date : April 26, 2017
 * </p><p>
 * TODO Description here
 * </p>
 *
 * @author Michael van Dyk
 */
public enum DateManager {
    ;
    /**
     *  Stores all the previously made Date objects. If the date is attempted to be
     * created again, it's current existing instance will be found in this before
     * making another Date instance with the same date.
     */
    private final static Map<Integer, DateReferenceCounter> DATE_MAP = new HashMap<>();

    /**
     * @return the total number of Date objects stored
     */
    public static int datesStored() {
        return (DATE_MAP.size());
    }

    /**
     *  Used to get a date object that represents the given year, month and day.
     * This is to prevent multiple Date objects being made that represent the
     * same date. If the date already has an associated object it is returned
     * when called. If not a new Date object is created, stored and returned.
     * @param year  the year of the date
     * @param month the month of the date
     * @param day   the day of the date
     * @return a Date object that represents the given date values
     */
    public static Date getDate(int year, Month month, int day) {
        return (getDate(year, month.getMonthNumber(), day));
    }

    /**
     *  Used to get a date object that represents the given year, month and day.
     * This is to prevent multiple Date objects being made that represent the
     * same date. If the date already has an associated object it is returned
     * when called. If not a new Date object is created, stored and returned.
     * @param year  the year of the date
     * @param month the month of the date
     * @param day   the day of the date
     * @return a Date object that represents the given date values
     */
    public static Date getDate(int year, int month, int day) {
        int date_val = Date.toDateVal(year, month, day);
        DateReferenceCounter get = DATE_MAP.get(date_val);
        Date ret;

        if (get == null) {
            Year.checkRange(year);
            /* No month check since Day.checkRange does the same check */
            Day.checkRange(year, month, day);

            DATE_MAP.put(date_val, new DateReferenceCounter(ret = new Date(date_val)));
        } else {
            get.inc();
            ret = get.date;
        }

        return (ret);
    }

    public static void removeDate(Date date) {
        int date_val = date.getDateVal();
        DateReferenceCounter get = DATE_MAP.get(date_val);

        if (get != null && get.dec() == 0) {
            DATE_MAP.remove(date_val);
        }
    }

    private static class DateReferenceCounter {
        private final Date date;
        private int count;

        protected DateReferenceCounter(Date date) {
            this.date = date;
            this.count = 1;
        }

        public int inc() {
            return (++this.count);
        }

        public int dec() {
            return (--this.count);
        }
    }
}
