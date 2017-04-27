package ehist.mem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <p>
 * === Position Class ===
 * </p><p>
 * Date : March 02, 2017
 * </p><p>
 * TODO Description here
 * </p>
 *
 * @author Michael van Dyk
 */
public class Position {

    private String title;
    private final Employer employer;

    private final List<PayPeriod> payPeriods;

    public Position(String title, Employer employer) {
        this.title = title;
        this.employer = employer;
        employer.addPosition(this);
        this.payPeriods = new ArrayList<>();
    }

    public void addPayPeriod(PayPeriod payPeriod) {
        payPeriods.add(payPeriod);
    }

    public Employer getEmployer() {
        return employer;
    }

    public List<PayPeriod> getPayPeriods() {
        return (Collections.unmodifiableList(payPeriods));
    }

    public String getTitle() {
        return title;
    }
}
