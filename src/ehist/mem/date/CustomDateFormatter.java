package ehist.mem.date;

/**
 * <p>
 * === CustomDateFormatter Class ===
 * </p><p>
 * Date : March 12, 2017
 * </p><p>
 *  This class is a special case of the {@link DateFormatter} to allow
 * for creating a custom formatter with a string that specifies the format.
 * This class is package local to remedy someone creating an instance
 * without making the call to {@link DateFormatter#createFormatter(String)}.
 * This method is intended to handle the creation of an instance and run any
 * checks needed to ensure the string is well formed. This class is mainly
 * hidden to de-clutter.
 * </p><p>
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
 * @see DateFormatter
 * @see Date
 *
 * @since EHist 1.0
 *
 * @author Michael van Dyk
 */
final class CustomDateFormatter implements DateFormatter {

    /** The string used in the formatting process. */
    private final String format;

    /**
     *  Creates a new custom date formatter. Restricted creation to the current package.
     * @param format the format string to use in the formatting process
     */
    protected CustomDateFormatter(String format) {
        this.format = format;
    }

    @Override
    public String format(Date date) {
        StringBuilder sb = new StringBuilder(format);

        int   day       = date.getDay();
        int   month_num = date.getMonthNumber();
        int   year      = date.getYear();
        Day   dow       = Day.dayOfWeek(year, month_num, day);
        Month month     = Month.getMonth(month_num);


        replaceAll(sb, "$d$",  Integer.toString(day));
        replaceAll(sb, "$dd$", String.format("%02d", day));
        replaceAll(sb, "$D$",  Day.formatNumber(day));

        replaceAll(sb, "$m$",  Integer.toString(month_num));
        replaceAll(sb, "$mm$", String.format("%02d", month_num));
        replaceAll(sb, "$M$",  month.getName());
        replaceAll(sb, "$Ms$", month.getShortForm());

        replaceAll(sb, "$y$",  Integer.toString(year));
        replaceAll(sb, "$yy$", String.format("%02d", year));

        replaceAll(sb, "$W$",  dow.getName());
        replaceAll(sb, "$Ws$", dow.getShortForm());

        return (sb.toString());
    }

    /**
     *  Replaces all instances of the 'from' string with the 'to'
     * string. Only added since string builder does not have its
     * own replace all.
     * @param sb   the string builder to replace all in, this is the output
     * @param from the string to replace
     * @param to   the string to replace with
     */
    private static void replaceAll(StringBuilder sb, final String from, final String to) {
        int index = sb.indexOf(from);

        while (index != -1) {
            sb.replace(index, index + from.length(), to);
            index = sb.indexOf(from, index + to.length());
        }
    }
}
