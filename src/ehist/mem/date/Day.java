package ehist.mem.date;

/**
 * <p>
 * === Day ===
 * </p><p>
 * Date : March 11, 2017
 * </p><p>
 *  A utility enum that holds operations related to days and can represent each day of the week.
 * </p>
 *
 * @see Date
 * @since EHist 1.0
 *
 * @author Michael van Dyk
 */
public enum Day {

    /** The day of the week Sunday. */
    SUNDAY("Sunday", "Sun"),

    /** The day of the week Monday. */
    MONDAY("Monday", "Mon"),

    /** The day of the week Tuesday. */
    TUESDAY("Tuesday", "Tues"),

    /** The day of the week Wednesday. */
    WEDNESDAY("Wednesday", "Wed"),

    /** The day of the week Thursday. */
    THURSDAY("Thursday", "Thurs"),

    /** The day of the week Friday. */
    FRIDAY("Friday", "Fri"),

    /** The day of the week Saturday. */
    SATURDAY("Saturday", "Sat");

    /** Used to easily retrieve each day of week when given a numeric value. */
    private static final Day[] DAYS = {SUNDAY, MONDAY, TUESDAY,
                                       WEDNESDAY, THURSDAY, FRIDAY,
                                       SATURDAY};

    /** The name of the day of the week. */
    private final String long_form;

    /** The short form of the day of the week. */
    private final String short_form;

    /**
     *  Initializes the enum value with the specified name and short form.
     * @param long_form  the name of the day of the week
     * @param short_form the short form of the day of the week
     */
    Day(String long_form, String short_form) {
        this.long_form = long_form;
        this.short_form = short_form;
    }

    /**
     *  Checks to see if a day is in range of the days of the week.
     * Out of range values will throw a {@link RuntimeException}.
     * @throws RuntimeException if the day value is out of range
     * @param day the number to check if in range
     */
    protected static void checkRange(int day) {
        if (day > 7 || day <= 0)
            throw new RuntimeException(day + " is not a valid value for the days of the week (1 <= day <= 7)");
    }

    /**
     *  Checks to see if a given date is in range.
     * @throws RuntimeException if the month value is not associated with a month
     * @throws RuntimeException if the day value is out of range
     * @param year  the year of the date to check
     * @param month the month of the date to check
     * @param day   the day of the date to check
     */
    protected static void checkRange(int year, int month, int day) {
        int days = Month.getMonth(month).getDays(Year.isLeapYear(year));
        if (day > days || days <= 0)
            throw new RuntimeException(String.format("%d is not a valid day value for %s %d (1 <= day <= %d)",
                    day, Month.getMonth(month).getName(), year, Month.getMonth(month).getDays(Year.isLeapYear(year))));
    }

    /**
     *  Gets the day of the week based on the date's year, month and
     * day. Algorithm is taken from:
     * https://cs.uwaterloo.ca/~alopez-o/math-faq/node73.html. That
     * sight should be consulted if for an understanding of the algorithm.
     * @param year  the year of the date
     * @param month the month of the date
     * @param day   the day of the date
     * @return the <tt>Day</tt> of the week associated with the given date
     */
    public static Day dayOfWeek(int year, int month, int day) {
        if (month < 3) {
            year -= 1;
            month += 10;
        } else {
            month -= 2;
        }

        int C = year / 100, Y = year % 100;

        int W = (day + (int)Math.floor(2.6 * month - 0.2) - 2 * C + Y + Y/4 + C/4);

        if (W < 0) W += (-W/7 + 1) * 7;

        return (DAYS[W % 7]);
    }

    /**
     *  Formats the number to have an attached st, nd, rd or th depending on their
     * conventions. This will not work correctly with negative numbers and negative
     * numbers should not be used. Only certain it works for numbers in the
     * range 1 to 31.
     * @param day the day to format
     * @return the formatted day
     */
    public static String formatNumber(int day) {
        String ret;

        if (day / 10 == 1) {
            ret = (day + "th");
        } else {
            switch (day % 10) {
                case 1:
                    ret = (day + "st");
                    break;
                case 2:
                    ret = (day + "nd");
                    break;
                case 3:
                    ret = (day + "rd");
                    break;
                default:
                    ret = (day + "th");
            }
        }
        return (ret);
    }

    /**
     *  Gets the day of the week associated with the given value.
     * @throws RuntimeException if the value is not associated with a day of the week
     * @param day the value of the day of the week to get
     * @return the day of the week associated with the given value
     */
    public static Day getDayOfWeek(int day) {
        checkRange(day);
        return (DAYS[day - 1]);
    }

    /**
     * @return the name of the day of the week
     */
    public String getName() {
        return (long_form);
    }

    /**
     * @return the short form of the day of the week
     */
    public String getShortForm() {
        return (short_form);
    }

}
