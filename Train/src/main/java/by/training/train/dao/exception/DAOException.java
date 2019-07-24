package by.training.train.dao.exception;

public class DAOException extends Exception {
    /**
     * Exception 1.
     */
    public DAOException() {
        super();
    }

    /**
     * Exception 2.
     *
     * @param message message.
     */
    public DAOException(final String message) {
        super(message);
    }

    /**
     * Exception 3.
     *
     * @param message message.
     * @param cause   cause.
     */
    public DAOException(final String message,
            final Throwable cause) {
        super(message, cause);
    }

    /**
     * Exception 4.
     *
     * @param cause cause.
     */
    public DAOException(final Throwable cause) {
        super(cause);
    }
}
