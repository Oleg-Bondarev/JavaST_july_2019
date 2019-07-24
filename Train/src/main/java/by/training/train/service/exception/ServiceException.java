package by.training.train.service.exception;

public class ServiceException extends Exception {
    /**
     * Exception 1.
     */
    public ServiceException() {
        super();
    }
    /**
     * Exception 2.
     *
     * @param message message.
     */
    public ServiceException(final String message) {
        super(message);
    }
    /**
     * Exception 3.
     *
     * @param message message.
     * @param cause   cause.
     */
    public ServiceException(final String message,
            final Throwable cause) {
        super(message, cause);
    }
    /**
     * Exception 4.
     *
     * @param cause cause.
     */
    public ServiceException(final Throwable cause) {
        super(cause);
    }
}
