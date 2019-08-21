package by.training.composite.service.parser;

import by.training.composite.entity.Component;
import by.training.composite.entity.LexemeComponent;
import by.training.composite.entity.SentenceComponent;

/**
 * Represent lexeme parser.
 * */
public class LexemeParser extends AbstractParse {
    /**
     * Regex for split sentence.
     * */
    private static final String LEXEMES_REGEX = "([ ]+)|(?<=[,:;])";
    /**
     * @param component -component.
     * @param part -text.
     * */
    @Override
    public void parse(final Component component, final String part) {
        String[] lexemesArray = part.split(LEXEMES_REGEX);
        if (component instanceof SentenceComponent) {
            SentenceComponent sentence = (SentenceComponent) component;
            for (String tempStr : lexemesArray) {
                LexemeComponent lexeme = new LexemeComponent();
                sentence.add(lexeme);
                chain(lexeme, tempStr.trim());
            }
        }
    }
}
