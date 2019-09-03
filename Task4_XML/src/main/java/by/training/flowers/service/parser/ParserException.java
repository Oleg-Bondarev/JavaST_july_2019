package by.training.flowers.service.parser;

/**
 * Parsing exception class.
 * */
public class ParserException extends Exception {
    /**
     * Constructor without parameters.
     * */
    public ParserException() {
        super();
    }
    /**
     * Constr. with one parameter.
     * @param message message.
     */
    public ParserException(final String message) {
        super(message);
    }
    /**
     * Constr. with two parameters.
     * @param message message.
     * @param cause   cause.
     */
    public ParserException(final String message,
                                final Throwable cause) {
        super(message, cause);
    }
    /**
     * Constr. with one parameter.
     * @param cause cause.
     */
    public ParserException(final Throwable cause) {
        super(cause);
    }

}
