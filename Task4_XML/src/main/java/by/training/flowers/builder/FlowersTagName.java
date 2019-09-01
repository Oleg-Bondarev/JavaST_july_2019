package by.training.flowers.builder;

public enum FlowersTagName {
    FLOWERS("flowers"),
    WILD_FLOWER("wild flower"),
    ARTIFICATIONAL_FLOWER("artificial flower"),
    SOIL("soil"),
    STEAM_COLOR("sream color"),
    LEAF_COLOR("leaf color"),
    AVERAGE_SIZE("average size"),
    TEMPERATURE("temperature"),
    WATERING("watering"),
    DISCOVERY_YEAR("discovery year"),
    IS_PROTECTED("is protected"),
    SCIENTIST("scientist"),
    SCIENTIST_NAME("scientist name");

    private String value;
    FlowersTagName(final String val) {
        this.value = val;
    }
    public String getValue() {
        return this.value;
    }
}
