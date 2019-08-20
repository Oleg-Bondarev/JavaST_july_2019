package by.training.composite.entity;

import java.util.List;
import java.util.StringJoiner;

/**
 * Represent text.
 * */
public class Text extends AbstractComposite {
    /**
     * Delimiter for text.
     * */
    private static final String TEXT_DELIMITER = "\n\t";
    /**
     * Tab for first paragraph.
     * */
    private static final String FIRST_TAB = "\t";
    /**
     * \n for last paragraph.
     * */
    private static final String LAST_NEW_LINE = "\n";
    /**
     * Type of the component.
     * */
    private final ComponentType componentType = ComponentType.TEXT;
    /**
     * Getter for component type.
     * */
    @Override
    public ComponentType getComponentType() {
        return this.componentType;
    }
    @Override
    public String compose() {
        StringJoiner stringJoiner = new StringJoiner(TEXT_DELIMITER,
                FIRST_TAB, LAST_NEW_LINE);
        List<Component> componentsList = getAllComponents();
        componentsList.forEach(tempCompanent
                                 -> stringJoiner.add(tempCompanent.compose()));
        return stringJoiner.toString();
    }
}
