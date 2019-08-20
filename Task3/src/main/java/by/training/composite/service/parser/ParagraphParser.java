package by.training.composite.service.parser;

import by.training.composite.entity.Component;
import by.training.composite.entity.Paragraph;
import by.training.composite.entity.Sentence;

/**
 * Represent paragraph parsing on sentences.
 * */
public class ParagraphParser extends AbstractParse {
    /**
     * Instance.
     * */
    private static final ParagraphParser INSTANCE = new ParagraphParser();
    /**
     * Regex for split paragraph.
     * Split !!! , ??? , ... , ?! , ?!
     * TODO . , ? , !
     * */
    private final String SENTENCE_REGEX = "(?<=[.])|(?<=[?])|(?<=[!])|(?<=\\?!)";
    /**
     * Constructor.
     * */
    private ParagraphParser() { }
    /**
     * Getter for instance.
     * @return class instance.
     * */
    public static ParagraphParser getInstance() {
        return INSTANCE;
    }

    @Override
    public void parse(final Component component, final String part) {
        Paragraph paragraph = (Paragraph) component;
        String[] sentencesArray = part.split(SENTENCE_REGEX);
        for (String tempStr : sentencesArray) {
            Sentence sentence = new Sentence();
            paragraph.add(sentence);
            chain(sentence, tempStr.trim());
        }
    }
}
