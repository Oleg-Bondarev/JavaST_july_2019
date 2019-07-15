package by.training.train.entity.enums;

public enum EconomClassEnum { /**type.*/
    RESERVEDSEAT("reservedseat"),
    /**type.*/
    COMMON("common");
    /**type of the econom-class carriage.*/
    private String classType;
    /**
     * Constructor.
     * @param classTypeNew - type.*/
    EconomClassEnum(final String classTypeNew) {
        this.classType = classTypeNew;
    }
    /**
     * Check if the string contains in the enum.
     * @param label -
     * @return result of checking.
     * */
    public static boolean isInEnum(final String label) {
        for (EconomClassEnum e : values()) {
            if (e.classType.equals(label.toLowerCase())) {
                return true;
            }
        }
        return false;
    }
}
