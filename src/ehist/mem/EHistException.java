package ehist.mem;

/**
 * <p>
 * === EHistException Class ===
 * </p><p>
 * Date : April 26, 2017
 * </p><p>
 * TODO Description here
 * </p>
 *
 * @author Michael van Dyk
 */
public class EHistException extends RuntimeException {

    public EHistException() {
        super();
    }

    public EHistException(String message) {
        super(message);
    }

    public EHistException(String message, Throwable cause) {
        super(message, cause);
    }

    public EHistException(Throwable cause) {
        super(cause);
    }

    protected EHistException(String message, Throwable cause,
                               boolean enableSuppression,
                               boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
