package by.training.composite.entity;

import java.util.List;

/**
 * Represent interface for using pattern.
 * */
public interface Component {
    String compose();

    ComponentType getComponentType();

    default void add(Component component) {
        throw new UnsupportedOperationException();
    }

    default void addComponents(List<Component> components) {
        throw new UnsupportedOperationException();
    }

    default void remove(Component component) {
        throw new UnsupportedOperationException();
    }

    default void removeAll() {
        throw new UnsupportedOperationException();
    }

    default Component getChild(int index) {
        throw new UnsupportedOperationException();
    }

    default int amountOfChildren() {
        return 1;
    }
}
