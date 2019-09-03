package by.training.flowers.service.properties;

/**
 * Property exception class.
 * */
public class PropertyException extends Exception {
    /**
     * Constructor without parameters.
     * */
    public PropertyException() {
        super();
    }
    /**
     * Constr. with one parameter.
     * @param message message.
     */
    public PropertyException(final String message) {
        super(message);
    }
    /**
     * Constr. with two parameters.
     * @param message message.
     * @param cause   cause.
     */
    public PropertyException(final String message,
                           final Throwable cause) {
        super(message, cause);
    }
    /**
     * Constr. with one parameter.
     * @param cause cause.
     */
    public PropertyException(final Throwable cause) {
        super(cause);
    }

}
