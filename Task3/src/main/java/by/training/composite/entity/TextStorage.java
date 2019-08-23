package by.training.composite.entity;

/**
 * Text storage class.
 * */
public final class TextStorage {
    /**
     * Instance.
     * */
    private static final TextStorage INSTANCE = new TextStorage();
    /**
     * Constructor.
     * */
    private TextStorage() { }
    /**
     * Text component.
     * */
    private Component textComponent;
    /**
     * Setter.
     * @param component -text component.
     * */
    public void setTextComponent(final Component component) {
        this.textComponent = component;
    }
    /**
     * Getter.
     * @return instance.
     * */
    public static TextStorage getInstance() {
        return INSTANCE;
    }
    /**
     * Getter.
     * @return text component.
     * */
    public Component getTextComponent() {
        return textComponent;
    }
}
