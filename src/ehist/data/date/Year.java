package ehist.data.date;

/**
 * <p>
 * === Year ===
 * </p><p>
 * Date : March 12, 2017
 * </p><p>
 *  A utility enum that only holds methods related to a year.
 * </p>
 *
 * @see Date
 * @since EHist 1.0
 *
 * @author Michael van Dyk
 */
public enum Year {
    ;

    /**
     * The maximum year that can be stored in the {@link Date} class. The minimum year is zero.
     */
    public final static int MAX_YEAR = 0x7FFFFF;

    /**
     *  Checks to see if a year is in range of what can be stored in {@link Date}.
     * Out of range values will throw a {@link InvalidYearException}.
     * @throws InvalidYearException if the year value is out of range
     * @param year the number to check if in range
     */
    protected static void checkRange(int year) {
        if (year < 0 || year > MAX_YEAR) {
            throw new InvalidYearException(String.format("%d is not a valid year (0 <= year <= %d)", year, MAX_YEAR));
        }
    }

    /**
     *  Used to determine the number of days in a year since leap years have an extra day.
     * @param year the year to determine for
     * @return the number of days of the specified year
     */
    public static int getDaysOfYear(int year) {
        return (isLeapYear(year) ? 366 : 365);
    }

    /**
     *  Determines if the given year is a leap year or not.
     * @param year the year to check
     * @return if the year is a leap year
     */
    public static boolean isLeapYear(int year) {
        return ((Integer.remainderUnsigned(year, 4) == 0) &&
                (Integer.remainderUnsigned(year, 100) != 0) ||
                (Integer.remainderUnsigned(year, 400) == 0));
    }

    /**
     * Distinguishes <tt>Year</tt> related exceptions from normal exceptions.
     * @see Year
     * @see Date.DateException
     * @since EHist 1.0
     */
    public static class InvalidYearException extends Date.DateException {

        /**
         * Allows for the exception to be thrown with a descriptive message.
         * @param message the message to describe the exception
         */
        public InvalidYearException(String message) {
            super (message);
        }
    }
}
