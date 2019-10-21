package by.training.final_task.service.validator;

import java.util.List;
import java.util.Map;

public class CompanyParametersValidator extends Validator implements Valid {

    private static final int COMPANY_NAME_INDEX = 0;
    private static final int COMPANY_ADDRESS_INDEX = 1;
    private static final int COMPANY_PHONE_INDEX = 2;

    private static final String REGEX_FOR_COMPANY_NAME = "^[a-zA-Zа-яА-Я0-9- ]+$";
    private static final String REGEX_FOR_COMPANY_ADDRESS = "^[a-zA-Zа-яА-Я0-9-\",. ]+$";
    private static final String REGEX_FOR_PHONE = "^((25)|(29)|(33)|(44))([0-9]{7}$)";

    private static final String[] params = {"incorrectName", "incorrectAddress",
                                            "incorrectPhone"};

    public int getCompanyNameIndex() {
        return COMPANY_NAME_INDEX;
    }

    public int getCompanyAddressIndex() {
        return COMPANY_ADDRESS_INDEX;
    }

    public int getCompanyPhoneIndex() {
        return COMPANY_PHONE_INDEX;
    }

    @Override
    public Map<String, Boolean> validate(final List<String> parameters) {
        Validator validator = new Validator();
        validator.checkPotentialAttack(parameters);
        Map<String, Boolean> validationMap = validator.initValidationMap(params);

        if (parameters.get(COMPANY_NAME_INDEX).matches(REGEX_FOR_COMPANY_NAME)) {
            validationMap.put("incorrectName", false);
        }
        if (parameters.get(COMPANY_ADDRESS_INDEX).matches(REGEX_FOR_COMPANY_ADDRESS)) {
            validationMap.put("incorrectAddress", false);
        }
        if (parameters.get(COMPANY_PHONE_INDEX).matches(REGEX_FOR_PHONE)) {
            validationMap.put("incorrectPhone", false);
        }
        return validationMap;
    }
}
