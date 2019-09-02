package by.training.flowers.entity;

/**
 * Multiplying enum.
 * */
public enum  Multiplying {
    /**Type.*/
    SEEDS("seeds"),
    /**Type.*/
    CUTTINGS("cuttings"),
    /**Type.*/
    LEAFS("leafs");
    /**Type value.*/
    private String value;
    /**
     * @param newValue -new value.
     * */
    Multiplying(final String newValue) {
        this.value = newValue;
    }
    /**
     * @return string.
     * */
    public String getValue() {
        return this.value;
    }
    /**
     * @param type -type.
     * @return object.
     * @throws UnknownTypeException -if have unknown type.
     * */
    public static Multiplying takeMultiplying(final String type)
            throws UnknownTypeException {
        for (Multiplying multiplying : Multiplying.values()) {
            if (multiplying.value.equals(type)) {
                return multiplying;
            }
        }
        throw new UnknownTypeException("Unknown multiplying type: " + type);
    }
}
