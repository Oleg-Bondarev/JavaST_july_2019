package by.training.composite.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

/**
 * Sentence component class.
 * */
public class SentenceComponent implements Component {
    /**
     * Lexemes.
     */
    private List<Component> lexemesList = new ArrayList<>();
    /**
     * Delimiter for sentences.
     * */
    private static final String SENTENCE_DELIMITER = " ";
    /**
     * For gathering strings.
     * @return string.
     * */
    @Override
    public String compose() {
        StringJoiner stringJoiner = new StringJoiner(SENTENCE_DELIMITER);
        lexemesList.forEach(tempComponent
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
            return lexemesList.get(index);
        }
    }
    /**
     * Remove component of composite.
     * @param component -component that we want to delete.
     */
    @Override
    public void remove(final Component component) {
        if (component != null) {
            lexemesList.remove(component);
        }
    }
    /**
     * Add component in composite.
     * @param newComponent - new component.
     */
    @Override
    public void add(final Component newComponent) {
        if (newComponent != null) {
            lexemesList.add(newComponent);
        }
    }
    /**
     * @return amount.
     * */
    @Override
    public int amountOfChildren() {
        return lexemesList.size();
    }
}
