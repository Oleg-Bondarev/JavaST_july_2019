package by.training.train.validator.validExc;

public class AmountException extends Exception {
    /**
     * Exception 1.
     */
    public AmountException() {
        super();
    }

    /**
     * Exception 2.
     * @param message message.
     */
    public AmountException(final String message) {
        super(message);
    }

    /**
     * Exception 3.
     * @param message message.
     * @param cause   cause.
     */
    public AmountException(final String message,
            final Throwable cause) {
        super(message, cause);
    }

    /**
     * Exception 4.
     * @param cause cause.
     */
    public AmountException(final Throwable cause) {
        super(cause);
    }
}
