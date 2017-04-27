package ehist.mem.date;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

/**
 * <p>
 * === Date Class ===
 * </p><p>
 * Date : March 02, 2017
 * </p><p>
 *  The <tt>Date</tt> class represents a specific date. Stores information
 * related to the year, month and day. To create a date use the method
 * {@link DateManager#getDate(int, int, int)} or {@link DateManager#getDate(int, Month, int)},
 * this is because this implementation of a date stores previously created dates
 * and this method will simply return the instance of the specified date if already
 * created. The retrieval of the date is constant time and will not slow the
 * application if a lot of dates are stored. The storage of date will also
 * not consume much memory as date are store in a minimal way. //TODO Update
 * </p>
 *
 * @since EHist 1.0
 *
 * @author Michael van Dyk
 */
public final class Date implements Comparable<Date> {

    /** The default formatter used in {@link Date#format()}. */
    private static final AtomicReference<DateFormatter> formatter = new AtomicReference<>(DateFormatter.YEAR_MM_DD);

    /**
     *  The value that is used to represent the date. The lowest 5 bits of
     * the integer are used to store the day (value range of 0 to 31). The
     * next 4 lowest bits are used for the month (values range 0 to 12).
     * The remaining bits are used to represent the year, it can store
     * years that are well past my life expectancy.
     */
    private final int date_val;

    /**
     *  Creates a date object.
     * @param date_val the compressed date value to store
     */
    protected Date(int date_val) {
        this.date_val = date_val;
    }

    @Override
    public int compareTo(Date o) {
        return (Integer.compareUnsigned(date_val, o.date_val));
    }

    /**
     *  Finds the number of days between two dates. It is inclusive.
     * @param a the lower date
     * @param b the upper date
     * @return the days between dates (inclusive)
     */
    public static int daysBetween(Date a, Date b) {
        /* The count of days */
        int days;

        if (a.compareTo(b) > 0) {
            /* Reverse the dates since the second date comes before the first */
            days = daysBetween(b, a);
        } else {
            /* Values related to the first date */
            int y1 = a.getYear(), m1 = a.getMonthNumber(), d1 = a.getDay();

            /* Values related to the second date*/
            int y2 = b.getYear(), m2 = b.getMonthNumber(), d2 = b.getDay();

            if (y1 == y2) {
                days = d2 - d1 + 1;

                // if the years are the same then you only need to consider if the months vary
                if (m1 != m2) {
                    days += Month.getDaysBetweenStart(m1, m2, Year.isLeapYear(y1));
                }
            } else {
                days = Month.getDaysFromEndOfYear(m1, Year.isLeapYear(y1)) - d1 + 1
                        + Month.getDaysFromStartOfYear(m2, Year.isLeapYear(y2)) + d2;

                // Add all the years that are between year of the lower and upper date (exclusive)
                for (int ys = y1 + 1; ys < y2; ++ys) {
                    days += (Year.isLeapYear(ys) ? 366 : 365);
                }
            }
        }

        return (days);
    }

    /** @return the day of the week this date represents */
    public Day dayOfWeek() {
        return (Day.dayOfWeek(getYear(), getMonthNumber(), getDay()));
    }

    /** @return the date formatted by the default formatter */
    public String format() {
        return (formatter.get().format(this));
    }

    /**
     *  Formats the date specified by the given formatter.
     * @param formatter the formatter to format the date
     * @return the formatted date
     */
    public String format(DateFormatter formatter) {
        return (formatter.format(this));
    }

    /**
     * @return the date value stored by this date
     */
    protected int getDateVal() {
        return (date_val);
    }

    /**
     * @return the day that this Date object represents
     */
    public int getDay() {
        return (getDay(date_val));
    }

    /**
     * @param date_val the date value to get the day from
     * @return the day represented in this date value
     */
    private static int getDay(int date_val) {
        return (date_val & 0x1F);
    }

    /**
     * @return the default formatter
     */
    public static DateFormatter getFormatter() {
        return (formatter.get());
    }

    /**
     * @return the month enum represented in this Date object
     */
    public Month getMonth() {
        return (Month.getMonth(getMonthNumber()));
    }

    /**
     * @return the month number represented in this Date object
     */
    public int getMonthNumber() {
        return (getMonthNumber(date_val));
    }

    /**
     * @param date_val the date value to get the month number from
     * @return the month number represented in this date value
     */
    private static int getMonthNumber(int date_val) {
        return ((date_val >>> 5) & 0xF);
    }

    /**
     * @return the year represented in this Date object
     */
    public int getYear() {
        return (getYear(date_val));
    }

    /**
     * @param date_val the date value to get the year from
     * @return the year represented in this date value
     */
    private static int getYear(int date_val) {
        return (date_val >>> 9);
    }

    /**
     * @return if this date is within a leap year
     */
    public boolean isLeapYear() {
        return (Year.isLeapYear(getYear()));
    }

    /**
     *  Sets the default formatter.
     * @param formatter the formatter that will be the new default
     */
    public static void setFormatter(DateFormatter formatter) {
        Date.formatter.set(formatter);
    }

    /**
     *  Gets the date value of the date.
     * @param year  the year of the date
     * @param month the month of the date
     * @param day   the day of the date
     * @return an integer that stores the date information
     */
    protected static int toDateVal(int year, int month, int day) {
        return ((year << 9) | (month << 5) | day);
    }

    @Override
    public String toString() {
        return (format());
    }
}
