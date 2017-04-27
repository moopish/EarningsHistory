package ehist.mem;

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
public final class Amount implements Comparable<Amount> {

    public final static Amount ZERO = new Amount(0);

    private final long cents;

    public Amount(long cents) {
        this.cents = cents;
    }

    public Amount(long dollars, long cents) {
        this.cents = dollars * 100 + cents;
    }

    public Amount add(Amount o) {
        return (new Amount(cents + o.cents));
    }

    public Amount subtract(Amount o) {
        return (new Amount(cents - o.cents));
    }

    @Override
    public int compareTo(Amount o) {
        return ((int)Math.signum(cents - o.cents));
    }

    @Override
    public String toString() {
        return (String.format("$%d.%02d", cents/100, cents%100));
    }

}
