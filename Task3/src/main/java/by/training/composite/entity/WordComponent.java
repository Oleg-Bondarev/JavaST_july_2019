package by.training.composite.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

/**
 * Word component.
 * */
public class WordComponent implements Component {
    /**
     * Symbols list.
     * */
    private List<Component> symbolsList = new ArrayList<>();
    /**
     * Type of component.
     * */
    private ComponentType componentType = ComponentType.WORD;
    /**
     * Default delimiter.
     * */
    private static final String DEFAULT_DELIMITER = "";

    /**
     * Getter.
     * @return type of component.
     * */
    public ComponentType getTypeComponent() {
        return componentType;
    }

    /**
     * For gathering text.
     * @return string.
     * */
    @Override
    public String compose() {
        StringJoiner stringJoiner = new StringJoiner(DEFAULT_DELIMITER);
        symbolsList.forEach(tempComp -> stringJoiner.add(tempComp.compose()));
        return stringJoiner.toString();
    }
    /**
     * @param index -index in composite.
     * @return child component.
     * */
    @Override
    public Component getChild(final int index) {
        if (index < 0) {
            return null;
        } else {
            return symbolsList.get(index);
        }
    }

    /**
     * Remove method.
     * @param component component.
     */
    @Override
    public void remove(final Component component) {
        if (component != null) {
            symbolsList.remove(component);
        }
    }

    /**
     * Add method.
     * @param newComponent component.
     */
    @Override
    public void add(final Component newComponent) {
        if (newComponent != null) {
            symbolsList.add(newComponent);
        }
    }
    /**
     * @return amount.
     * */
    @Override
    public int amountOfChildren() {
        return symbolsList.size();
    }
}
