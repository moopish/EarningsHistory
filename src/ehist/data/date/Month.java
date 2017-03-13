package ehist.data.date;

/**
 * <p>
 * === Month ===
 * </p><p>
 * Date : March 11, 2017
 * </p><p>
 *  An enum to handle month related operations. Contains an enumerated
 * value for each month that can be used to get the name and short form
 * of the month as well as in operations.
 * </p>
 *
 * @see Date
 * @since EHist 1.0
 *
 * @author Michael van Dyk
 */
public enum Month {

    /** The month of January. */
    JANUARY("January", "Jan"),

    /** The month of February. */
    FEBRUARY("February", "Feb"),

    /** The month of March. */
    MARCH("March", "Mar"),

    /** The month of April. */
    APRIL("April", "Apr"),

    /** The month of May. */
    MAY("May", "May"),

    /** The month of June. */
    JUNE("June", "Jun"),

    /** The month of July. */
    JULY("July", "Jul"),

    /** The month of August. */
    AUGUST("August", "Aug"),

    /** The month of September. */
    SEPTEMBER("September", "Sept"),

    /** The month of October. */
    OCTOBER("October", "Oct"),

    /** The month of November. */
    NOVEMBER("November", "Nov"),

    /** The month of December. */
    DECEMBER("December", "Dec");

    /** Used to easily retrieve each month when given a numeric value for a month. */
    private final static Month[] MONTHS = {JANUARY, FEBRUARY, MARCH, APRIL,
                                     MAY, JUNE, JULY, AUGUST, SEPTEMBER,
                                     OCTOBER, NOVEMBER, DECEMBER};

    /** The name of the month. */
    private final String long_name;

    /** The short form name of the month */
    private final String short_name;

    /**
     *  Initializes the enum value with the specified name and short form.
     * @param long_name  the name of the month
     * @param short_name the short form of the month
     */
    Month(String long_name, String short_name) {
        this.long_name = long_name;
        this.short_name = short_name;
    }

    /**
     *  Checks to see if a numerical month value is in range.
     * Out of range values will throw a {@link InvalidMonthException}.
     * @throws InvalidMonthException if the month value is out of range
     * @param month the number to check if in range
     */
    protected static void checkRange(int month) {
        if (month <= 0 || month > 12) {
            throw new InvalidMonthException("Value " + month + " is out of range [1<=month<=12]");
        }
    }

    /**
     *  Gets the number of days in the month.
     * @param isLeapYear required for February
     * @return the number of days in the month
     */
    public int getDays(boolean isLeapYear) {
        int days;

        switch (this) {
            case JANUARY:
            case MARCH:
            case MAY:
            case JULY:
            case AUGUST:
            case OCTOBER:
            case DECEMBER:
                days = 31;
                break;

            case APRIL:
            case JUNE:
            case SEPTEMBER:
            case NOVEMBER:
                days = 30;
                break;

            case FEBRUARY:
                days = (isLeapYear ? 29 : 28);
                break;

            default:
                days = 0;
        }
        return (days);
    }

    /**
     * <p>
     *  Finds the number of days between the start of months. Excludes
     * the first day of the second month specified. If the second month
     * comes before the first in order during the year, the number of
     * days returned will be from the first month to the second month
     * in the next year.
     * </p><p>
     *     Caution must be taken when the second month comes before the
     * first in order during the year. The <tt>isLeapYear</tt> may cause
     * an extra day or a lack of a day.
     * </p>
     *
     * @throws InvalidMonthException if either month value is not valid
     * @param a          the first month
     * @param b          the second month
     * @param isLeapYear required for February
     * @return the days between the starts of months
     */
    public static int getDaysBetweenStart(int a, int b, boolean isLeapYear) {
        return (getDaysBetweenStart(getMonth(a), getMonth(b), isLeapYear));
    }

    /**
     * <p>
     *  Finds the number of days between the start of months. Excludes
     * the first day of the second month specified. If the second month
     * comes before the first in order during the year, the number of
     * days returned will be from the first month to the second month
     * in the next year.
     * </p><p>
     *     Caution must be taken when the second month comes before the
     * first in order during the year. The <tt>isLeapYear</tt> may cause
     * an extra day or a lack of a day.
     * </p>
     *
     * @param a          the first month
     * @param b          the second month
     * @param isLeapYear required for February
     * @return the days between the starts of months
     */
    public static int getDaysBetweenStart(Month a, Month b, boolean isLeapYear) {
        return ((a.ordinal() > b.ordinal())
                ? (b.getDaysFromEndOfYear(isLeapYear) + a.getDaysFromStartOfYear(isLeapYear))
                : (b.getDaysFromStartOfYear(isLeapYear) - a.getDaysFromStartOfYear(isLeapYear)));
    }

    /**
     * Gives the number of days to the start of a month from the end of the year.
     * @param isLeapYear required for February
     * @return the number of days from the end of the year
     */
    public int getDaysFromEndOfYear(boolean isLeapYear) {
        int days;
        switch (this) {
            case DECEMBER:
                days = (31);
                break;

            case NOVEMBER:
                days = (61);
                break;

            case OCTOBER:
                days = (92);
                break;

            case SEPTEMBER:
                days = (122);
                break;

            case AUGUST:
                days = (153);
                break;

            case JULY:
                days = (184);
                break;

            case JUNE:
                days = (214);
                break;
            
            case MAY:
                days = (245);
                break;
            
            case APRIL:
                days = (275);
                break;
            
            case MARCH:
                days = (306);
                break;
            
            case FEBRUARY:
                days = (334 + (isLeapYear ? 1 : 0));
                break;
            
            case JANUARY:
            default:
                days = (365 + (isLeapYear ? 1 : 0));
        }
        
        return (days);
    }

    /**
     * Gives the number of days to the start of a month from the end of the year.
     * @throws InvalidMonthException if the month value given is invalid
     * @param month      the month to consider
     * @param isLeapYear required for February
     * @return the number of days from the end of the year
     */
    public static int getDaysFromEndOfYear(int month, boolean isLeapYear) {
        return (getMonth(month).getDaysFromEndOfYear(isLeapYear));
    }

    /**
     * Gives the number of days to the start of a month from the start of the year.
     * @param isLeapYear required for February
     * @return the number of days from the start of the year
     */
    public int getDaysFromStartOfYear(boolean isLeapYear) {
        int days;

        switch (this) {
            case JANUARY:
                days = 0;
                break;

            case FEBRUARY:
                days = 31;
                break;

            case MARCH:
                days = 59 + (isLeapYear ? 1 : 0);
                break;

            case APRIL:
                days = 90 + (isLeapYear ? 1 : 0);
                break;

            case MAY:
                days = 120 + (isLeapYear ? 1 : 0);
                break;

            case JUNE:
                days = 151 + (isLeapYear ? 1 : 0);
                break;

            case JULY:
                days = 181 + (isLeapYear ? 1 : 0);
                break;

            case AUGUST:
                days = 212 + (isLeapYear ? 1 : 0);
                break;

            case SEPTEMBER:
                days = 243 + (isLeapYear ? 1 : 0);
                break;

            case OCTOBER:
                days = 273 + (isLeapYear ? 1 : 0);
                break;

            case NOVEMBER:
                days = 304 + (isLeapYear ? 1 : 0);
                break;

            case DECEMBER:
            default:
                days = 334 + (isLeapYear ? 1 : 0);
        }

        return (days);
    }

    /**
     * Gives the number of days to the start of a month from the start of the year.
     * @throws InvalidMonthException if the month value given is invalid
     * @param month      the month to consider
     * @param isLeapYear required for February
     * @return the number of days from the start of the year
     */
    public static int getDaysFromStartOfYear(int month, boolean isLeapYear) {
        return (getMonth(month).getDaysFromStartOfYear(isLeapYear));
    }

    /**
     *  Gets the month enum associated with the given number
     * @throws InvalidMonthException if the value given is not associated with a month enum
     * @param month the month to get the enum for
     * @return the enum of the specified month
     */
    public static Month getMonth(int month) {
        checkRange(month);
        return (MONTHS[month-1]);
    }

    /**
     * @return the numerical value associated with the month
     */
    public int getMonthNumber() {
        return (ordinal() + 1);
    }

    /**
     * @return the name of the month
     */
    public String getName() {
        return (long_name);
    }

    /**
     *  Gets the name of the month specified by the numerical value
     * @throws InvalidMonthException if the value is invalid
     * @param month the month value to get the month name for
     * @return the name of the month
     */
    public static String getName(int month) {
        return (getMonth(month).long_name);
    }

    /**
     * @return the short form of the month
     */
    public String getShortForm() {
        return (short_name);
    }

    /**
     *  Gets the short form name of the month specified by the numerical value
     * @throws InvalidMonthException if the value is invalid
     * @param month the month value to get the month short form for
     * @return the short form of the month
     */
    public static String getShortForm(int month) {
        return (getMonth(month).short_name);
    }

    /**
     * Distinguishes <tt>Month</tt> related exceptions from normal exceptions.
     * @see Month
     * @see Date.DateException
     * @since EHist 1.0
     */
    public static final class InvalidMonthException extends Date.DateException {

        /**
         * Allows for the exception to be thrown with a descriptive message.
         * @param message the message to describe the exception
         */
        public InvalidMonthException(String message) {
            super(message);
        }
    }
}
