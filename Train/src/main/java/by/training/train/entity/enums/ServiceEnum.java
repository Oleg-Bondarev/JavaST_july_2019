package by.training.train.entity.enums;

public enum ServiceEnum {
    /**Vip service.*/
    VIP("vip"),
    /**Buisness service.*/
    BUISINESS("buisness"),
    /**Lux service.*/
    LUX("lux"),
    /**Econom service.*/
    ECONOM("econom");
    /**Type of service in the carriage.*/
    private String serviceType;
    /**
     * Constructor.
     * @param serviceTypeNew - type.*/
    ServiceEnum(final String serviceTypeNew) {
        this.serviceType = serviceTypeNew;
    }
    /**
     * Check if the string contains in the enum.
     * @param label -
     * @return result of checking.
     * */
    public static boolean isInEnum(final String label) {
        for (ServiceEnum e : values()) {
            if (e.serviceType.equals(label.toLowerCase())) {
                return true;
            }
        }
        return false;
    }
}
