package by.training.composite.service.parser;

import by.training.composite.entity.Component;
import by.training.composite.entity.ParagraphComponent;
import by.training.composite.entity.TextComponent;

/**
 * Represent paragraph parsing on sentences.
 * */
public class ParagraphParser extends AbstractParse {
     /**
     * Delimiter for parsing text on paragraphs.
     * */
    private static final String PARAGRAPH_REGEX = "(\t)|[ ]{4}";
    /**
     * Paragraph parsing.
     * @param component -component.
     * @param part -text for parsing.
     * */
    @Override
    public void parse(final Component component, final String part) {
        String[] paragraphArray = part.split(PARAGRAPH_REGEX);
        if (component instanceof TextComponent) {
            TextComponent text = (TextComponent) component;
            for (String tempStr : paragraphArray) {
                if (tempStr.equals("")) {
                    continue;
                }
                ParagraphComponent paragraph = new ParagraphComponent();
                text.add(paragraph);
                chain(paragraph, tempStr.trim());
            }
        }
    }
}
