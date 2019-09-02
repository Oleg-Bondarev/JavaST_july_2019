package by.training.flowers.builder;

/**
 * Tag enum.
 * */
public enum FlowersTagName {
    /**Tag.*/
    FLOWERS("flowers"),
    /**Tag.*/
    WILD_FLOWER("wild"),
    /**Tag.*/
    ARTIFICATIONAL_FLOWER("artificial"),
    /**Tag.*/
    NAME("name"),
    /**Tag.*/
    IS_MEDICAL("isMedical"),
    /**Tag.*/
    MULTIPLYING("multiplying"),
    /**Tag.*/
    ID("id"),
    /**Tag.*/
    SOIL("soil"),
    /**Tag.*/
    STEAM_COLOR("steam_color"),
    /**Tag.*/
    LEAF_COLOR("leaf_color"),
    /**Tag.*/
    AVG_SIZE("avg_size"),
    /**Tag.*/
    TEMPERATURE("temperature"),
    /**Tag.*/
    WATERING("watering"),
    /**Tag.*/
    LIGHTING("lighting"),
    /**Tag.*/
    DISCOVERY_YEAR("discovery_year"),
    /**Tag.*/
    IS_PROTECTED("is-protected"),
    /**Tag.*/
    SCIENTIST("scientist"),
    /**Tag.*/
    SCIENTIST_NAME("scientist_name");
    /**
     * Tag value.
     * */
    private String value;
    /**
     * @param val -new value.
     * */
    FlowersTagName(final String val) {
        this.value = val;
    }
    /**
     * @return string.
     * */
    public String getValue() {
        return this.value;
    }
}
