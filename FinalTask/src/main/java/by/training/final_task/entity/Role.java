package by.training.final_task.entity;

public enum Role {
    ADMIN("admin"),
    USER("user"),
    GUEST("guest");

    private String value;

    Role(final String newValue) {
        value = newValue;
    }

    public String getValue() {
        return value;
    }

    public static Role fromValue(final String newValue)
            throws WrongEnumTupeException {
        for (Role role : Role.values()) {
            if (role.value.equals(newValue)) {
                return role;
            }
        }
        throw new WrongEnumTupeException(newValue);
    }

    public static Role valueOf(final int index) {
        return Role.values()[index];
    }

    public int getOrdinal() {
        return ordinal();
    }
}
