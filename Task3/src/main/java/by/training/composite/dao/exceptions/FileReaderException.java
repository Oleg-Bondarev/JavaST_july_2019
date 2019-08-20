package by.training.composite.dao.exceptions;

/**
 * Represents processing of file reader exceptions.
 * */
public class FileReaderException extends Exception {
    /**
     * Constructor without parameters.
     * */
    public FileReaderException() {
        super();
    }
    /**
     * Constr. with one parameter.
     * @param message message.
     */
    public FileReaderException(final String message) {
        super(message);
    }
    /**
     * Constr. with two parameters.
     * @param message message.
     * @param cause   cause.
     */
    public FileReaderException(final String message,
                            final Throwable cause) {
        super(message, cause);
    }
    /**
     * Constr. with one parameter.
     * @param cause cause.
     */
    public FileReaderException(final Throwable cause) {
        super(cause);
    }
}
