package by.training.train.dao.exception;

public class FileWriterException extends Exception {
    /**
     * Exception 1.
     */
    public FileWriterException() {
        super();
    }

    /**
     * Exception 2.
     *
     * @param message message.
     */
    public FileWriterException(final String message) {
        super(message);
    }

    /**
     * Exception 3.
     *
     * @param message message.
     * @param cause   cause.
     */
    public FileWriterException(final String message,
            final Throwable cause) {
        super(message, cause);
    }

    /**
     * Exception 4.
     *
     * @param cause cause.
     */
    public FileWriterException(final Throwable cause) {
        super(cause);
    }
}
