package by.training.composite.controller;

/**
 * Represents processing of incorrect index exceptions.
 * */
public class IncorrectIndexException extends Exception {
    /**
     * Constructor without parameters.
     * */
    public IncorrectIndexException() {
        super();
    }
    /**
     * Constr. with one parameter.
     * @param message message.
     */
    public IncorrectIndexException(final String message) {
        super(message);
    }
    /**
     * Constr. with two parameters.
     * @param message message.
     * @param cause   cause.
     */
    public IncorrectIndexException(final String message,
                                   final Throwable cause) {
        super(message, cause);
    }
    /**
     * Constr. with one parameter.
     * @param cause cause.
     */
    public IncorrectIndexException(final Throwable cause) {
        super(cause);
    }
}
