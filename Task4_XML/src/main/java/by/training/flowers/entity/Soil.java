package by.training.flowers.entity;

/**
 * Soil type.
 * */
public enum Soil {
    /**Type.*/
    PODZOLIC("podzolic"),
    /**Type.*/
    UNPAVED("unpaved"),
    /**Type.*/
    SOD_PODZOLIC("sod-podzolic");
    /**
     * Type value.
     * */
    private String value;
    /**
     * @param newValue -value.
     * */
    Soil(final String newValue) {
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
