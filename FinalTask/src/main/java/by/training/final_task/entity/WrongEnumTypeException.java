package by.training.final_task.entity;

/**
 * Represents processing of incorrect enum type exceptions.
 * */
public class WrongEnumTypeException extends Exception {
    /**
     * Constructor without parameters.
     * */
    public WrongEnumTypeException() {
        super();
    }
    /**
     * Constr. with one parameter.
     * @param message message.
     */
    public WrongEnumTypeException(final String message) {
        super(message);
    }
    /**
     * Constr. with two parameters.
     * @param message message.
     * @param cause   cause.
     */
    public WrongEnumTypeException(final String message,
                                  final Throwable cause) {
        super(message, cause);
    }
    /**
     * Constr. with one parameter.
     * @param cause cause.
     */
    public WrongEnumTypeException(final Throwable cause) {
        super(cause);
    }
}

