package by.training.composite.service.parser;

import by.training.composite.entity.Component;
import by.training.composite.entity.ParagraphComponent;
import by.training.composite.entity.SentenceComponent;

/**
 * Represent sentence parser.
 * */
public class SentenceParser extends AbstractParse {
     /**
     * Regex for split paragraph.
     * */
    private static final String SENTENCE_REGEX = "(?<=[\\.\\!\\?\\t])\\s";
    /**
     * Parsing method.
     * @param component -component.
     * @param part -text.
     * */
    @Override
    public void parse(final Component component, final String part) {
        String[] sentencesArray = part.split(SENTENCE_REGEX);
        if (component instanceof ParagraphComponent) {
            ParagraphComponent paragraph = (ParagraphComponent) component;
            for (String tempStr : sentencesArray) {
                SentenceComponent sentence = new SentenceComponent();
                paragraph.add(sentence);
                chain(sentence, tempStr.trim());
            }
        }
    }
}
