package by.training.train.dao.exception;

public class FileReaderException extends Exception {
    /**
     * Exception 1.
     */
    public FileReaderException() {
        super();
    }

    /**
     * Exception 2.
     *
     * @param message message.
     */
    public FileReaderException(final String message) {
        super(message);
    }

    /**
     * Exception 3.
     *
     * @param message message.
     * @param cause   cause.
     */
    public FileReaderException(final String message,
            final Throwable cause) {
        super(message, cause);
    }

    /**
     * Exception 4.
     *
     * @param cause cause.
     */
    public FileReaderException(final Throwable cause) {
        super(cause);
    }
}
