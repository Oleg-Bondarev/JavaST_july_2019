package by.training.composite.service.parser;

import by.training.composite.entity.Component;
import by.training.composite.entity.Lexeme;
import by.training.composite.entity.Sentence;

import java.util.ArrayList;
import java.util.List;

/**
 * Represent sentence parser.
 * */
public class SentenceParser extends AbstractParse {
    /**
     * Instance.
     * */
    private static final SentenceParser INSTANCE = new SentenceParser();
    /**
     * Regex for split sentence.
     * */
    private final String LEXEMES_REGEX = "[ ]+";
    /**
     * Constructor.
     * */
    private SentenceParser() { }
    /**
     * Getter for instance.
     * @return class instance.
     * */
    public static SentenceParser getInstance() {
        return INSTANCE;
    }

    @Override
    public void parse(Component component, String part) {
        Sentence sentence = (Sentence) component;
        String[] lexemesArray = part.split(LEXEMES_REGEX);
        for (String tempStr : lexemesArray) {
            Lexeme lexeme = new Lexeme();
            sentence.add(lexeme);
            chain(lexeme, tempStr.trim());
        }
    }
}
