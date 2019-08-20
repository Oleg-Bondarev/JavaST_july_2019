package by.training.composite.service.parser;

import by.training.composite.entity.Component;

public abstract class AbstractParse {
    /**
     * Next parser.
     * */
    protected AbstractParse nextParser;
    /**
     * Setter for next parser.
     * */
    public void setNextParser(final AbstractParse parser) {
        this.nextParser = parser;
    }

    public abstract void parse(final Component component, final String part);
    public void chain(final Component component, final String part) {
        nextParser.parse(component, part);
    }
}
