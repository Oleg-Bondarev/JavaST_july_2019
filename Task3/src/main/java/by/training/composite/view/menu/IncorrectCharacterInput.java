package by.training.composite.view.menu;

/**
 * Incorrect input of the character exception.
 * */
public class IncorrectCharacterInput extends Exception {
    /**
     * Constructor.
     */
    public IncorrectCharacterInput() {
        super();
    }
    /**
     * @param message message.
     */
    public IncorrectCharacterInput(final String message) {
        super(message);
    }
    /**
     * @param message message.
     * @param cause   cause.
     */
    public IncorrectCharacterInput(final String message,
                            final Throwable cause) {
        super(message, cause);
    }
    /**
     * @param cause cause.
     */
    public IncorrectCharacterInput(final Throwable cause) {
        super(cause);
    }
}
