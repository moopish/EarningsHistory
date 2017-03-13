package ehist.data.date;

/**
 * <p>
 * === DateFormatter ===
 * </p><p>
 * Date : March 12, 2017
 * </p><p>
 *  A way to format a given {@link Date} to a string representing that date. The call
 * to {@link DateFormatter#format(Date)} will return the formatted string.
 * </p><p>
 *     Several pre-made <tt>DateFormatter</tt> methods are available in this interface.
 * </p>
 *
 * @see Date
 *
 * @since EHist 1.0
 *
 * @author Michael van Dyk
 */
public interface DateFormatter {

    /**
     *  Formats a string to represent the given date.
     * @param date the date to format a string of
     * @return a string representing the date
     */
    String format(Date date);

    /**
     * Formats the date by day/month/year.
     */
    DateFormatter DAY_MONTH_YEAR = (Date date) ->
            (String.format("%d/%d/%d", date.getDay(), date.getMonthNumber(), date.getYear()));

    /**
     * Formats the date by day/month/year, month and day are formatted to have a leading zero if a single digit.
     */
    DateFormatter DD_MM_YEAR = (Date date) ->
            (String.format("%02d/%02d/%d", date.getDay(), date.getMonthNumber(), date.getYear()));

    /**
     * Formats the date by day/month/year, all are formatted to have a leading zero if a single digit.
     */
    DateFormatter DD_MM_YY = (Date date) ->
            (String.format("%02d/%02d/%02d", date.getDay(), date.getMonthNumber(), date.getYear()));

    /**
     * Formats the date similar to 'Sunday, March 12, 2017'.
     */
    DateFormatter DOW_MONTH_DAY_YEAR = (Date date) ->
            (String.format("%s, %s %d, %d", date.dayOfWeek().getName(), date.getMonth().getName(),
                    date.getDay(), date.getYear()));

    /**
     * Formats the date similar to 'Sunday, March 12th, 2017'.
     */
    DateFormatter DOW_MONTH_DAY_TH_YEAR = (Date date) ->
            (String.format("%s, %s %s, %d", date.dayOfWeek().getName(), date.getMonth().getName(),
                    Day.formatNumber(date.getDay()), date.getYear()));

    /**
     * Formats the date similar to 'Sun, Mar 12, 2017'.
     */
    DateFormatter SHORT_DOW_MONTH_DAY_YEAR = (Date date) ->
            (String.format("%s, %s %d, %d", date.dayOfWeek().getShortForm(), date.getMonth().getShortForm(),
                    date.getDay(), date.getYear()));

    /**
     * Formats the date similar to 'Sun, Mar 12th, 2017'.
     */
    DateFormatter SHORT_DOW_MONTH_DAY_TH_YEAR = (Date date) ->
            (String.format("%s, %s %s, %d", date.dayOfWeek().getShortForm(), date.getMonth().getShortForm(),
                    Day.formatNumber(date.getDay()), date.getYear()));

    /**
     * Formats the date by year/month/day, month and day are formatted to have a leading zero if a single digit.
     */
    DateFormatter YEAR_MM_DD = (Date date) ->
            (String.format("%d/%02d/%02d", date.getYear(), date.getMonthNumber(), date.getDay()));

    /**
     * Formats the date by year/month/day.
     */
    DateFormatter YEAR_MONTH_DAY = (Date date) ->
            (String.format("%d/%d/%d", date.getYear(), date.getMonthNumber(), date.getDay()));

    /**
     * Formats the date by year/month/day, all are formatted to have a leading zero if a single digit.
     */
    DateFormatter YY_MM_DD = (Date date) ->
            (String.format("%02d/%02d/%02d", date.getYear(), date.getMonthNumber(), date.getDay()));

    /**
     * <p>
     *     Creates a DateFormatter that will follow the rules of the given string to display the date.
     *   If you know how to create a DateFormatter without using this method, do so as it will create a
     *   likely quicker method of formatting.
     * </p>
     * <p>
     *  The string can use the special format keys:
     * <ul>
     *     <li><tt>$d$</tt> - for presenting a day number without formatting</li>
     *     <li><tt>$dd$</tt> - for presenting a day number formatted so single digit days are lead with a zero</li>
     *     <li><tt>$D$</tt> - for presenting a day number with ending st/nd/rd/th</li>
     *     <li><tt>$m$</tt> - for presenting a month number without formatting</li>
     *     <li><tt>$mm$</tt> - for presenting a month number formatted so single digit months are lead with a zero</li>
     *     <li><tt>$M$</tt> - for presenting a month as its name</li>
     *     <li><tt>$Ms$</tt> - for presenting a month as its short form name</li>
     *     <li><tt>$y$</tt> - for presenting a year without formatting</li>
     *     <li><tt>$yy$</tt> - for presenting a year formatted so single digit years are lead with a zero</li>
     *     <li><tt>$W$</tt> - for presenting a day of the week as its name</li>
     *     <li><tt>$Ws$</tt> - for presenting a day of the week as its short form name</li>
     * </ul></p>
     *
     * @param format the format string following the rules above
     * @return the DateFormatter that will format based on the given format string
     */
    static DateFormatter createFormatter(String format) {
        return (new CustomDateFormatter(format));
    }

}
