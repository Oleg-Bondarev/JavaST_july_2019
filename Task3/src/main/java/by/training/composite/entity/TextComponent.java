package by.training.composite.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

/**
 * Text component class.
 * */
public class TextComponent implements Component {
    /**
     * List of paragraphs.
     * */
    private List<Component> paragraphsList = new ArrayList<>();
    /**
     * Component type.
     * */
    private ComponentType componentType = ComponentType.TEXT;
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
     * Getter.
     * @return component type.
     * */
    public ComponentType getComponentType() {
        return componentType;
    }
    /**
     * For gathering text.
     * @return text.
     * */
    @Override
    public String compose() {
        StringJoiner stringJoiner = new StringJoiner(TEXT_DELIMITER,
                FIRST_TAB, LAST_NEW_LINE);
        paragraphsList.forEach(tempComponent
                -> stringJoiner.add(tempComponent.compose()));
        return stringJoiner.toString();
    }
    /**
     * @param index -component index.
     * @return child component.
     * */
    @Override
    public Component getChild(final int index) {
        if (index < 0) {
            return null;
        } else {
            return paragraphsList.get(index);
        }
    }
    /**
     * Remove component of composite.
     * @param component -component that we want to delete.
     */
    @Override
    public void remove(final Component component) {
        if (component != null) {
            paragraphsList.remove(component);
        }
    }
    /**
     * Add component in composite.
     * @param newComponent - new component.
     */
    @Override
    public void add(final Component newComponent) {
        if (newComponent != null) {
            paragraphsList.add(newComponent);
        }
    }
    /**
     * @return amount.
     * */
    @Override
    public int amountOfChildren() {
        return paragraphsList.size();
    }
}
