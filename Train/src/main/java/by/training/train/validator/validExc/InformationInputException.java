package by.training.train.validator.validExc;

public class InformationInputException extends Exception {
    /**
     * Exception 1.
     */
    public InformationInputException() {
        super();
    }

    /**
     * Exception 2.
     * @param message message.
     */
    public InformationInputException(final String message) {
        super(message);
    }

    /**
     * Exception 3.
     * @param message message.
     * @param cause   cause.
     */
    public InformationInputException(final String message,
            final Throwable cause) {
        super(message, cause);
    }

    /**
     * Exception 4.
     * @param cause cause.
     */
    public InformationInputException(final Throwable cause) {
        super(cause);
    }
}
