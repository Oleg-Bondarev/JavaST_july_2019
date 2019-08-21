package by.training.composite.service.parser;

import by.training.composite.entity.Component;

/**
 * Represent parsing text on paragraphs.
 * */
public class TextParser extends AbstractParse {
    /**
     * Text parsing.
     * @param component -component.
     * @param part -text for parsing.
     * */
    @Override
    public void parse(final Component component, final String part) {
        chain(component, part);
    }
}
