package by.training.train.entity.enums;

public enum  PassengerCarriageEnum {
    /**Compartment class carriage.*/
    COMPARTMENT("compartment"),
    /**Econom-class carriage.*/
    ECONOMCLASS("economclass"),
    /**Siting class carriage.*/
    SEATING("seating"),
    /**Restaurant class carriage.*/
    RESTAURANT("restaurant");
    /**comfort type of the carriage.*/
    private String comfortType;
    /**
     * Constructor.
     * @param comfortTypeNew - type.*/
    PassengerCarriageEnum(final String comfortTypeNew) {
        this.comfortType = comfortTypeNew;
    }
    /**
     * Check if the string contains in the enum.
     * @param label -
     * @return result of checking.
     * */
    public static boolean isInEnum(final String label) {
        for (PassengerCarriageEnum e : values()) {
            if (e.comfortType.equals(label.toLowerCase())) {
                return true;
            }
        }
        return false;
    }
}
