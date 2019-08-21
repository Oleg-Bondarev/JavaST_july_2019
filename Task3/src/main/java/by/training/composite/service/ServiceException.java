package by.training.composite.service;

/**
 * Service exception.
 * */
public class ServiceException extends Exception {
    /**
     * Constructor.
     */
    public ServiceException() {
        super();
    }
    /**
     * @param message message.
     */
    public ServiceException(final String message) {
        super(message);
    }
    /**
     * @param message message.
     * @param cause   cause.
     */
    public ServiceException(final String message,
                            final Throwable cause) {
        super(message, cause);
    }
    /**
     * @param cause cause.
     */
    public ServiceException(final Throwable cause) {
        super(cause);
    }
}

