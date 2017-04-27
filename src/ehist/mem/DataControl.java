package ehist.mem;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * === DataControl Class ===
 * </p><p>
 * Date : March 13, 2017
 * </p><p>
 * TODO Description here
 * </p>
 *
 * @author Michael van Dyk
 */
public class DataControl {

    private final List<Employer> employers;
    private final List<DeductionType> deductionTypes;
    private final List<Deduction> deductions;
    private final List<Position> positions;
    private final List<PayPeriod> payPeriods;

    public DataControl() {
        this.deductions = new ArrayList<>();
        this.deductionTypes = new ArrayList<>();
        this.employers = new ArrayList<>();
        this.payPeriods = new ArrayList<>();
        this.positions = new ArrayList<>();
    }
}
