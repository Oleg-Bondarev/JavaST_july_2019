package by.training.final_task.entity;

/**
 * Represents roles in the system.
 * */
public enum Role {
    /**Role.*/
    ADMIN("admin"),
    /**Role.*/
    USER("user"),
    /**Role.*/
    STAFF("staff"),
    /**Role.*/
    GUEST("guest");

    /**
     * String value of enum fields.
     * */
    private String value;
    /**
     * Sets new string parameter.
     * @param newValue to be set to {@link #value}
     * */
    Role(final String newValue) {
        value = newValue;
    }
    /**
     * @return {@link #value}
     * */
    public String getValue() {
        return value;
    }
    /**
     * @param newValue by what string value we wont to get role.
     * @throws WrongEnumTypeException if enum fields don't contains this value.
     * @return role object.
     * */
    public static Role fromValue(final String newValue)
            throws WrongEnumTypeException {
        for (Role role : Role.values()) {
            if (role.value.equals(newValue)) {
                return role;
            }
        }
        throw new WrongEnumTypeException(newValue);
    }
    /**
     * @param index to get value of role.
     * @return role object by index in enum.
     * */
    public static Role valueOf(final int index) {
        return Role.values()[index];
    }
    /**
     * @return ordinal.
     * */
    public int getOrdinal() {
        return ordinal();
    }
}
