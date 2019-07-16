package by.training.train.repository.exception;

public class InvalidIndexIDException extends Exception {
    /**
     * Exception 1.
     */
    public InvalidIndexIDException() {
        super();
    }

    /**
     * Exception 2.
     * @param message message.
     */
    public InvalidIndexIDException(final String message) {
        super(message);
    }

    /**
     * Exception 3.
     * @param message message.
     * @param cause   cause.
     */
    public InvalidIndexIDException(final String message,
            final Throwable cause) {
        super(message, cause);
    }

    /**
     * Exception 4.
     * @param cause cause.
     */
    public InvalidIndexIDException(final Throwable cause) {
        super(cause);
    }
}
