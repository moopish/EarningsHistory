package ehist.mem;


import ehist.mem.date.Date;

/**
 * <p>
 * === PayPeriod Class ===
 * </p><p>
 * Date : March 02, 2017
 * </p><p>
 * TODO Description here
 * </p>
 *
 * @author Michael van Dyk
 */
public class PayPeriod {

    private final Date start;
    private final Date end;

    private final Position position;

    private final Amount gross;
    private final Deduction[] deductions;

    public PayPeriod(Date start, Date end, Position position, Amount gross, Deduction[] deductions) {
        if (start == null) {
            throw new EHistException("PayPeriod: start date cannot be null");
        } else if (end == null) {
            throw new EHistException("PayPeriod: end date cannot be null");
        } else if (position == null) {
            throw new EHistException("PayPeriod: position cannot be null");
        } else if (gross == null) {
            throw new EHistException("PayPeriod: gross cannot be null");
        } else if (deductions == null) {
            throw new EHistException("PayPeriod: deductions cannot be null");
        }

        this.start = start;
        this.end = end;

        this.position = position;
        position.addPayPeriod(this);

        this.gross = gross;
        this.deductions = deductions;
    }

    public ReadOnlyArray<Deduction> getDeductions() {
        return (new ReadOnlyArray<>(deductions));
    }

    public Date getEndDate() {
        return end;
    }

    public Amount getGrossAmount() {
        return gross;
    }

    public Amount getDeductionAmount() {
        Amount sum = Amount.ZERO;

        for (Deduction d : deductions) {
            sum = sum.add(d.getAmount());
        }

        return (sum);
    }

    public Amount getNetAmount() {
        return (gross.subtract(getDeductionAmount()));
    }

    public Position getPosition() {
        return position;
    }

    public Date getStartDate() {
        return start;
    }
}