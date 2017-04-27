package ehist.mem.date;

/**
 * <p>
 * === DateArithmetic Class ===
 * </p><p>
 * Date : March 12, 2017
 * </p><p>
 * TODO Description here
 * </p>
 *
 * @author Michael van Dyk
 */
public class DateArithmetic {

    public static Date add(Date date, int years, int months, int weeks, int days) {
        int new_year = date.getYear() + years;
        int current_month = date.getMonthNumber();
        int new_month = current_month + months;
        int new_days = 7 * weeks + days + date.getDay();

        //TODO reduce days

        //TODO reduce months
        if (new_month > 0) {
            new_year += (new_month - 1) / 12;
            new_month = ((new_month - 1) % 12) + 1;
        } else {
            new_year += new_month/12 - 1;
            new_month = new_month + 12 * (-new_month/12 + 1);
        }

        return (null);
    }

}
