package by.training.composite.service.parser;

import by.training.composite.entity.Symbol;
import by.training.composite.entity.Component;
import by.training.composite.entity.Word;

/**
 * Represent parsing word.
 * */
public class WordParser extends AbstractParse {
    /**
     * Instance.
     * */
    private static final WordParser INSTANCE = new WordParser();
    /**
     * Constructor.
     * */
    private WordParser() { }
    /**
     * Getter for instance.
     * @return class instance.
     * */
    public static WordParser getInstance() {
        return INSTANCE;
    }

    @Override
    public void parse(Component component, String part) {
        Word word = (Word) component;
        char[] symbolArray = part.toCharArray();
        for (char character : symbolArray) {
            Symbol symbol = new Symbol(character);
        }
    }
}
