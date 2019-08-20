package by.training.composite.service.parser;

import by.training.composite.entity.Component;
import by.training.composite.entity.Paragraph;
import by.training.composite.entity.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Represent parsing text on paragraphs.
 * */
public class TextParser extends AbstractParse {
    /**
     * Instance.
     * */
    private static final TextParser INSTANCE = new TextParser();
    /**
     * Delimiter for parsing text on paragraphs.
     * */
    private final String PARAGRAPH_REGEX = "(\t)|[ ]{4}";
    /**
     * Constructor.
     * */
    private TextParser() { }
    /**
     * Getter for instance.
     * @return class instance.
     * */
    public static TextParser getInstance() {
        return INSTANCE;
    }

    @Override
    public void parse(final Component component, final String part) {
        Text text = (Text) component;
        String[] paragraphArray = part.split(PARAGRAPH_REGEX);
        for (String tempStr : paragraphArray) {
            if (tempStr.equals("")) {
                continue;
            }
            Paragraph paragraph = new Paragraph();
            text.add(paragraph);
            chain(paragraph, tempStr.trim());
        }
    }
}
