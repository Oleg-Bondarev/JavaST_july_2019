package by.training.final_task.service.validator;

import java.util.List;
import java.util.Map;

public class CouponParametersValidator extends Validator implements Valid {

    private static final int COUPON_NAME = 0;
    private static final int COUPON_DESCRIPTION = 1;
    private static final int COUPON_PRICE = 2;
    private static final int HOLDING_ADDRESS = 3;

    public static final String REGEX_FOR_COUPON_NAME = "^[a-zA-Zа-яёА-ЯЁ0-9- ]+$";
    public static final String REGEX_FOR_COUPON_DESCRIPTION = "^[a-zA-Zа-яёА-ЯЁ0-9-\",.!? ]+$";
    public static final String REGEX_FOR_COUPON_PRICE = "^[1-9]{1}[\\d]{0,2}\\.[\\d]{2}$";
    public static final String REGEX_FOR_ADDRESS = "^[a-zA-Zа-яёА-ЯЁ\\d-\",. ]+$";

    private static final String[] params =
                                {"incorrectCouponName", "incorrectDescription",
                                "incorrectPrice", "incorrectAddress"};

    public int getCouponNameIndex() {
        return COUPON_NAME;
    }

    public int getCouponDescriptionIndex() {
        return COUPON_DESCRIPTION;
    }

    public int getCouponPriceIndex() {
        return COUPON_PRICE;
    }

    public int getHoldingAddressIndex() {
        return HOLDING_ADDRESS;
    }

    @Override
    public Map<String, Boolean> validate(List<String> parameters) {
        Validator validator = new Validator();
        validator.checkPotentialAttack(parameters);
        Map<String, Boolean> validationMap = validator.initValidationMap(params);

        if (parameters.get(COUPON_NAME).matches(REGEX_FOR_COUPON_NAME)) {
            validationMap.put("incorrectCouponName", false);
        }
        if (parameters.get(COUPON_DESCRIPTION)
                .matches(REGEX_FOR_COUPON_DESCRIPTION)) {
            validationMap.put("incorrectDescription", false);
        }
        if (parameters.get(COUPON_PRICE).matches(REGEX_FOR_COUPON_PRICE)) {
            validationMap.put("incorrectPrice", false);
        }
        if (parameters.get(HOLDING_ADDRESS).matches(REGEX_FOR_ADDRESS)) {
            validationMap.put("incorrectAddress", false);
        }
        return validationMap;
    }
}
