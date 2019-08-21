package by.training.composite.entity;

/**
 * Symbol component class.
 * */
public class SymbolComponent implements Component {
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
     * For gathering string.
     * @return string.
     * */
    @Override
    public String compose() {
        return String.valueOf(character);
    }
}
