package by.training.multithreading_matrix.entity;

/**
 * Exception class.
 * */
public class MatrixException extends Exception {
    /**
     * Constructor without parameters.
     * */
    public MatrixException() {
        super();
    }
    /**
     * Constr. with one parameter.
     * @param message message.
     * */
    public MatrixException(final String message) {
        super(message);
    }
    /**
     * Constr. with two parameters.
     * @param message message.
     * @param cause   cause.
     * */
    public MatrixException(final String message, final Throwable cause) {
        super(message, cause);
    }
    /**
     * Constr. with one parameter.
     * @param cause cause.
     * */
    public MatrixException(final Throwable cause) {
        super(cause);
    }
}
