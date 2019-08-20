package by.training.composite.dao.exceptions;

/**
 * Represents processing of file writer exceptions.
 * */
public class FileWriterException extends Exception {
    /**
     * Constructor without parameters.
     * */
    public FileWriterException() {
        super();
    }
    /**
     * Constr. with one parameter.
     * @param message message.
     */
    public FileWriterException(final String message) {
        super(message);
    }
    /**
     * Constr. with two parameters.
     * @param message message.
     * @param cause   cause.
     */
    public FileWriterException(final String message, final Throwable cause) {
        super(message, cause);
    }
    /**
     * Constr. with one parameter.
     * @param cause cause.
     */
    public FileWriterException(final Throwable cause) {
        super(cause);
    }
}
