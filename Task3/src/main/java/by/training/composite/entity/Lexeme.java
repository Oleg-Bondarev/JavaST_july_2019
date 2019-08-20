package by.training.composite.entity;

/**
 * Represent lexeme.
 * */
public class Lexeme extends AbstractComposite {
    /**
     * Component type.
     * */
    private final ComponentType componentType = ComponentType.LEXEME;

    @Override
    public ComponentType getComponentType() {
        return this.componentType;
    }
}
