package by.training.composite.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

/**
 * Abstract class.
 * */
public abstract class AbstractComposite implements Component {
    /**
     * List of components.
     * */
    private List<Component> components = new ArrayList<>();
    /**
     * Default delimiter.
     * */
    private static final String DEFAULT_DELIMITER = "";

    @Override
    public String compose() {
        StringJoiner stringJoiner = new StringJoiner(DEFAULT_DELIMITER);
        components.forEach(tempComp -> stringJoiner.add(tempComp.compose()));
        return stringJoiner.toString();
    }
    /**
     * Getter.
     * @return components list.
     * */
    protected List<Component> getAllComponents() {
        return new ArrayList<>(components);
    }
    /**
     * Add method for component.
     * @param component -new component.
     * */
    @Override
    public void add(final Component component) {
        if (component != null) {
            this.components.add(component);
        }
    }
    @Override
    public void addComponents(final List<Component> newComponents) {
        if (components != null) {
            this.components.addAll(newComponents);
        }
    }
    /**
     * Getter for component.
     * @param index -index.
     * @return component.
     * */
    @Override
    public Component getChild(final int index) {
        return (index < 0) ? null : this.components.get(index);
    }
    /**
     * Remove method.
     * @param component -what we want to remove.
     * */
    @Override
    public void remove(final Component component) {
        if (component != null) {
            this.components.remove(component);
        }
    }
    @Override
    public void removeAll() {
        this.components.clear();
    }
    @Override
    public int amountOfChildren() {
        return components.size();
    }
}
