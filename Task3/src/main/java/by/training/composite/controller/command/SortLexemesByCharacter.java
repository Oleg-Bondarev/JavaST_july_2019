package by.training.composite.controller.command;

import by.training.composite.controller.Command;
import by.training.composite.entity.Component;
import by.training.composite.entity.TextStorage;
import by.training.composite.service.interfaces.SortSpecification;
import by.training.composite.service.sort.SortLexemesByCharacterEntry;

/**
 * Sorting lexemes by  entry character.
 * */
public class SortLexemesByCharacter implements Command {
    /**
     * Character for sorting.
     * */
    private char character;
    /**
     * Constructor.
     * @param newCharacter -character for sorting.
     * */
    public SortLexemesByCharacter(final char newCharacter) {
        this.character = newCharacter;
    }
    /**
     * Execute method.
     * */
    @Override
    public void execute() {
        TextStorage textStorage = TextStorage.getInstance();
        Component component = textStorage.getTextComponent();
        SortSpecification sortParagraphs
                = new SortLexemesByCharacterEntry(character);
        sortParagraphs.sort(component);
    }
}
