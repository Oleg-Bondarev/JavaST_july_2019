package by.training.composite.entity;

import java.util.List;
import java.util.StringJoiner;

/**
 * Represent sentences.
 * */
public class Sentence extends AbstractComposite {
    /**
     * Delimiter for sentences.
     * */
    private static final String SENTENCE_DELIMITER = " ";
    /**
     * Component type.
     * */
    private final ComponentType componentType = ComponentType.SENTENCE;

    @Override
    public ComponentType getComponentType() {
        return this.componentType;
    }
    @Override
    public String compose() {
        StringJoiner stringJoiner = new StringJoiner(SENTENCE_DELIMITER);
        List<Component> componentList = getAllComponents();
        componentList.forEach(tempComponent
                -> stringJoiner.add(tempComponent.compose()));
        return stringJoiner.toString();
    }
}
