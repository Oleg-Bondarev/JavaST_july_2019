package by.training.composite.service.parser;

import by.training.composite.entity.Component;
import by.training.composite.entity.Lexeme;
import by.training.composite.entity.Punctuation;
import by.training.composite.entity.Word;

import java.util.ArrayList;
import java.util.List;

/**
 * Represent lexeme parser.
 * */
public class LexemeParser extends AbstractParse {
    /**
     * Instance.
     * */
    private static final LexemeParser INSTANCE = new LexemeParser();
    /**
     * Regex.
     * */
    private static final String WORD_REGEX = "(\\w+-?)+";
    /**
     * Regex.
     * TODO ... ?!
     * */
    private static final String WORD_PUNCTUATION_REGEX = "(?<=(\\.|,|\\?|!))";
    /**
     * Constructor.
     * */
    private LexemeParser() { }
    /**
     * Getter for instance.
     * @return class instance.
     * */
    public static LexemeParser getInstance() {
        return INSTANCE;
    }

    @Override
    public void parse(Component component, String part) {
        Lexeme lexeme = (Lexeme) component;
        if (part.matches(WORD_REGEX)) {
            Word word = new Word();
            lexeme.add(word);
            chain(word, part);
        } else if (part.matches(WORD_PUNCTUATION_REGEX)) {
            String[] tempArray = part.split(WORD_PUNCTUATION_REGEX);
            Word word = new Word();
            Punctuation punctuation = new Punctuation();
            lexeme.add(word);
            lexeme.add(punctuation);
            chain(word, tempArray[0]);
            chain(punctuation, tempArray[1]);
        } else {
            Punctuation punctuation = new Punctuation();
            lexeme.add(punctuation);
            chain(punctuation, part);
        }
    }
}
