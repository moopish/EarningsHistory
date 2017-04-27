package ehist.mem;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * === DeductionType Class ===
 * </p><p>
 * Date : March 02, 2017
 * </p><p>
 * TODO Description here
 * </p>
 *
 * @author Michael van Dyk
 */
public class DeductionType {

    private String name;
    private final List<Deduction> deductions;

    public DeductionType(String name) {
        this.name = name;
        this.deductions = new ArrayList<>();
    }

    public void addDeduction(Deduction deduction) {
        deductions.add(deduction);
    }

    public String getName() {
        return (name);
    }
}
