package ehist.mem;

/**
 * <p>
 * === Deduction Class ===
 * </p><p>
 * Date : March 02, 2017
 * </p><p>
 * TODO Description here
 * </p>
 *
 * @author Michael van Dyk
 */
public class Deduction {

    private Amount amount;
    private final DeductionType deductionType;

    public Deduction(Amount amount, DeductionType deductionType) {
        if (amount == null) {
            throw  new EHistException("Deduction: amount cannot be null");
        } else if (deductionType == null) {
            throw new EHistException("Deduction: deductionType cannot be null");
        }

        this.amount = amount;
        this.deductionType = deductionType;
        deductionType.addDeduction(this);
    }

    public Amount getAmount() {
        return (amount);
    }
}
