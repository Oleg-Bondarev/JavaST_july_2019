package by.training.composite.service.parser;

import by.training.composite.entity.Component;

/**
 * Abstract class.
 * */
public abstract class AbstractParse {
    /**
     * Next parser.
     * */
    private AbstractParse nextParser;
    /**
     * Setter for next parser.
     * @param parser -nex parser.
     * */
    public void setNextParser(final AbstractParse parser) {
        this.nextParser = parser;
    }
    /**
     * Abstract parsing method.
     * @param component -component.
     * @param part -text.
     * */
    public abstract void parse(Component component, String part);
    /**
     * Chain method.
     * @param component -component.
     * @param part -part.
     * */
    void chain(final Component component, final String part) {
        nextParser.parse(component, part);
    }
}
