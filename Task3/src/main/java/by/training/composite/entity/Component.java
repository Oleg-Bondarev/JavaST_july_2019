package by.training.composite.entity;

/**
 * Represent interface of composite.
 * */
public interface Component {
    /**
     * Gather text.
     * @return text.
     * */
    String compose();
    /**
     * @param component -new component.
     * */
    default void add(Component component) {
        throw new UnsupportedOperationException();
    }
    /**
     * @param component -component that we want to remove.
     * */
    default void remove(Component component) {
        throw new UnsupportedOperationException();
    }
    /**
     * @param index -component index.
     * @return child component.
     * */
    default Component getChild(int index) {
        throw new UnsupportedOperationException();
    }
    /**
     * Amount of children.
     * @return amount.
     * */
    default int amountOfChildren() {
        return 1;
    }
}
