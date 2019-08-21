package by.training.composite.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

/**
 * Paragraph component class.
 * */
public class ParagraphComponent implements Component {
    /**
     * List of sentences.
     * */
    private List<Component> sentencesList = new ArrayList<>();
    /**
     * Delimiter for sentences.
     * */
    private static final String SENTENCE_DELIMITER = " ";
    /**
     * Component type.
     * */
    private ComponentType componentType = ComponentType.SENTENCE;

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
        StringJoiner stringJoiner = new StringJoiner(SENTENCE_DELIMITER);
        sentencesList.forEach(tempComponent
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
            return sentencesList.get(index);
        }
    }
    /**
     * Remove component of composite.
     * @param component -component that we want to delete.
     */
    @Override
    public void remove(final Component component) {
        if (component != null) {
            sentencesList.remove(component);
        }
    }
    /**
     * Add component in composite.
     * @param newComponent - new component.
     */
    @Override
    public void add(final Component newComponent) {
        if (newComponent != null) {
            sentencesList.add(newComponent);
        }
    }
    /**
     * @return amount.
     * */
    @Override
    public int amountOfChildren() {
        return sentencesList.size();
    }
}
