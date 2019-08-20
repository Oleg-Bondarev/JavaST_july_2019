package by.training.composite.entity;

/**
 * Represent characters of the word.
 * */
public class Symbol extends AbstractComposite {
    /**
     * Component type.
     * */
    private final ComponentType componentType = ComponentType.SYMBOL;
    /**
     * Character.
     * */
    private char character;

    /**
     * Constructor.
     * */
    public Symbol(final char newCharacter) {
        this.character = newCharacter;
    }
    @Override
    public ComponentType getComponentType() {
        return this.componentType;
    }
    @Override
    public String compose() {
        return String.valueOf(character);
    }
}
