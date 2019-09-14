package by.training.final_task.entity;

/**
 * Represents processing of incorrect enum type exceptions.
 * */
public class WrongEnumTupeException extends Exception {
    /**
     * Constructor without parameters.
     * */
    public WrongEnumTupeException() {
        super();
    }
    /**
     * Constr. with one parameter.
     * @param message message.
     */
    public WrongEnumTupeException(final String message) {
        super(message);
    }
    /**
     * Constr. with two parameters.
     * @param message message.
     * @param cause   cause.
     */
    public WrongEnumTupeException(final String message,
                                   final Throwable cause) {
        super(message, cause);
    }
    /**
     * Constr. with one parameter.
     * @param cause cause.
     */
    public WrongEnumTupeException(final Throwable cause) {
        super(cause);
    }
}

