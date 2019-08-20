package by.training.composite.entity;

/**
 * Represents punctuation in the lexeme.
 * Can be ... , !? , ?! , ??? , !!!
 * */
public class Punctuation extends AbstractComposite {
    /**
     * Component type.
     * */
    private final ComponentType componentType = ComponentType.PUNCTUATION;

    @Override
    public ComponentType getComponentType() {
        return this.componentType;
    }
}
