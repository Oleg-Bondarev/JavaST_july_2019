package by.training.composite.entity;

/**
 * Represent word.
 * */
public class Word extends AbstractComposite {
    /**
     * Component type.
     * */
    private final ComponentType componentType = ComponentType.WORD;

    @Override
    public ComponentType getComponentType() {
        return this.componentType;
    }
}
