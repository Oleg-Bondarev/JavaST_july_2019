package by.training.flowers.factory;
/**
 * Parser type exception.
 * */
public class UnknownParserTypeException extends Exception {
    /**
     * Constructor without parameters.
     * */
    public UnknownParserTypeException() {
         super();
    }
    /**
     * Constr. with one parameter.
     * @param message message.
     */
    public UnknownParserTypeException(final String message) {
        super(message);
    }
    /**
     * Constr. with two parameters.
     * @param message message.
     * @param cause   cause.
     */
    public UnknownParserTypeException(final String message,
                                final Throwable cause) {
        super(message, cause);
    }
    /**
     * Constr. with one parameter.
     * @param cause cause.
     */
    public UnknownParserTypeException(final Throwable cause) {
        super(cause);
    }
}
