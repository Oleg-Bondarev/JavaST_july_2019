package by.training.composite.entity;

import java.util.List;
import java.util.StringJoiner;

/**
 * Represent paragraphs.
 * */
public class Paragraph extends AbstractComposite {
    /**
     * Delimiter for paragraph.
     * */
    private static final String PARAGRAPH_DELIMITER = " ";
    /**
     * Type of component.
     * */
    private final ComponentType componentType = ComponentType.PARAGRAPH;

    @Override
    public ComponentType getComponentType() {
        return this.componentType;
    }
    @Override
    public String compose() {
        StringJoiner stringJoiner = new StringJoiner(PARAGRAPH_DELIMITER);
        List<Component> componentList = getAllComponents();
        componentList.forEach(tempComponent
                                -> stringJoiner.add(tempComponent.compose()));
        return stringJoiner.toString();
    }
}
