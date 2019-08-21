package by.training.composite.entity;

/**
 * Symbol component class.
 * */
public class SymbolComponent implements Component {
    /**
     * Component type.
     * */
    private ComponentType componentType = ComponentType.SYMBOL;
    /**
     * Character.
     * */
    private char character;

    /**
     * Constructor.
     * @param newCharacter -new character.
     * */
    public SymbolComponent(final char newCharacter) {
        this.character = newCharacter;
    }
    /**
     * Getter.
     * @return component type.
     * */
    public ComponentType getComponentType() {
        return this.componentType;
    }
    /**
     * For gathering string.
     * @return string.
     * */
    @Override
    public String compose() {
        return String.valueOf(character);
    }
}
