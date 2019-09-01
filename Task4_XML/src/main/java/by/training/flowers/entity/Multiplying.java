package by.training.flowers.entity;

/**
 * Multiplying enum.
 * */
public enum  Multiplying {
    SEEDS("seeds"),
    CUTTINGS("cuttings"),
    LEAFS("leafs");

    private String value;

    Multiplying(final String newValue) {
        this.value = newValue;
    }
    public String getValue() {
        return this.value;
    }
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
