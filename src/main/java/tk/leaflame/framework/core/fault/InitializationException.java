package tk.leaflame.framework.core.fault;

/**
 * @author leaflame
 * @date 2020/3/3 9:26
 */
public class InitializationException extends Exception {

    private static final String MESSAGE = "initialization error!";

    public InitializationException() {
        this(MESSAGE);
    }

    public InitializationException(String message) {
        super(message);
    }

    public InitializationException(String message, Throwable cause) {
        super(message, cause);
    }

    public InitializationException(Throwable cause) {
        super(cause);
    }

    public InitializationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
