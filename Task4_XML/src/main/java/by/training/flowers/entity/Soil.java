package by.training.flowers.entity;

/**
 * Soil type.
 * */
public enum Soil {
    PODZOLIC("podzolic"),
    UNPAVED("unpaved"),
    SOD_PODZOLIC("sod-podzolic");

    private String value;

    Soil(final String newValue) {
        this.value = newValue;
    }

    public String getValue() {
        return this.value;
    }

    public static Soil takeSoilType(final String type)
            throws UnknownTypeException {
        for (Soil soil : Soil.values()) {
            if (soil.value.equals(type)) {
                return soil;
            }
        }
        throw new UnknownTypeException("Unknown soil type: " + type);
    }
}
