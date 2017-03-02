package util;

/**
 * <p>
 * === Amount Class ===
 * </p><p>
 * Date : March 02, 2017
 * </p><p>
 * TODO Description here
 * </p>
 *
 * @author Michael van Dyk
 */
public final class Amount {

    private static final Operator<Long> _ADD = ((a, b) -> (a + b));
    private static final Operator<Long> _SUBTRACT = ((a, b) -> (a - b));

    private static final Operator<Long> _DIV_DOWN = ((a, b) -> (a / b));

    //TODO the following two
    private static final Operator<Long> _DIV_NEAREST = ((a, b) -> (a / b));
    private static final Operator<Long> _DIV_UP = ((a, b) -> (a / b));


    public enum RoundingType {
        ROUND_DOWN(_DIV_DOWN),
        ROUND_NEAREST(_DIV_NEAREST),
        ROUND_UP(_DIV_UP);

        private final Operator<Long> operator;

        RoundingType(Operator<Long> operator) {
            this.operator = operator;
        }
    }

    private final long cents;

    public Amount(long cents) {
        this.cents = cents;
    }

    public Amount(long dollars, long cents) {
        this.cents = dollars * 100 + cents;
    }

    public Amount add(Amount o) {
        return (_operation(o, _ADD));
    }

    public static Amount addMultiple(Amount... amounts) {
        return (_operation(_SUBTRACT, amounts));
    }

    public double decimal() {
        return (this.cents/100.0);
    }

    public Amount divide(long divisor) {
        return (divide(divisor, RoundingType.ROUND_DOWN));
    }

    public Amount divide(long divisor, RoundingType rtype) {
        if (divisor == 0) {
            //TODO err
        }
        return (_operation(divisor, rtype.operator));
    }

    public Amount subtract(Amount o) {
        return (_operation(o, _SUBTRACT));
    }

    public static Amount subtractMultiple(Amount... amounts) {
        return (_operation(_SUBTRACT, amounts));
    }

    @Override
    public String toString() {
        return ("$" + decimal());
    }

    private Amount _operation(long o, Operator<Long> operator) {
        return (new Amount(operator.op(cents, o)));
    }

    private Amount _operation(Amount o, Operator<Long> operator) {
        //TODO ERROR CHECKING
        return (new Amount(operator.op(cents, o.cents)));
    }

    private static Amount _operation(Operator<Long> operator, Amount... os) {
        //TODO ERROR CHECKING
        if (os.length <= 0) {
            //TODO
            return (null);
        } else if (os.length == 1) {
            return (os[0]);
        } else {
            long result = os[0].cents;

            for (int i=1; i<os.length; ++i) {
                result = operator.op(result, os[i].cents);
            }

            return (new Amount(result));
        }
    }

    private interface Operator<E> {
        E op(E a, E b);
    }

}
