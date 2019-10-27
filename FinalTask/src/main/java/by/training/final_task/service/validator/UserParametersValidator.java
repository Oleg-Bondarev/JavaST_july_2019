package by.training.final_task.service.validator;

import java.util.List;
import java.util.Map;

public class UserParametersValidator extends Validator implements Valid {

    private static final int USER_LOGIN = 0;
    private static final int USER_PASSWORD = 1;
    private static final int USER_EMAIL = 2;
    private static final int USER_FIRST_NAME = 3;
    private static final int USER_SECOND_NAME = 4;
    private static final int USER_MOBILE_PHONE = 5;
    public static final String USER_LOGIN_REGEX = "^[a-zA-Z0-9]{4,16}$";
    public static final String USER_PASSWORD_REGEX = "^[a-zA-Z0-9]{8,16}$";
    public static final String USER_EMAIL_REGEX =
            "^([a-z0-9_-]+\\.)*[a-z0-9_-]+@[a-z0-9_-]+(\\.[a-z0-9_-]+)*\\.[a-z]{2,6}$";
    public static final String USER_FIRST_NAME_RU_REGEX = "^[а-яА-Я-]{2,10}$";
    public static final String USER_SECOND_NAME_RU_REGEX = "^[а-яА-Я-]{2,20}$";
    public static final String USER_FIRST_NAME_EN_REGEX = "^[a-zA-Z-]{2,10}$";
    public static final String USER_SECOND_NAME_EN_REGEX = "^[a-zA-Z-]{2,20}$";
    public static final String USER_MOBILE_REGEX =
            "^((25)|(29)|(33)|(44))([0-9]{7}$)";

    private static final String[] PARAMS =
            {"incorrectLogin", "incorrectPassword",
                    "incorrectEmail", "incorrectFirstName",
                    "incorrectSecondName", "incorrectMobilePhone"};

    public Map<String, Boolean> validate(final List<String> newParams) {
        Validator validator = new Validator();
        validator.checkPotentialAttack(newParams);
        Map<String, Boolean> validationMap = validator
                .initValidationMap(PARAMS);

        if (newParams.get(USER_LOGIN).matches(USER_LOGIN_REGEX)) {
            validationMap.put("incorrectLogin", false);
        }
        if (newParams.get(USER_PASSWORD).matches(USER_PASSWORD_REGEX)) {
            validationMap.put("incorrectPassword", false);
        }
        if (newParams.get(USER_EMAIL).matches(USER_EMAIL_REGEX)) {
            validationMap.put("incorrectEmail", false);
        }
        String firstName = newParams.get(USER_FIRST_NAME);
        String secondName = newParams.get(USER_SECOND_NAME);
        if ((firstName.matches(USER_FIRST_NAME_RU_REGEX)
                && secondName.matches(USER_SECOND_NAME_RU_REGEX))
                || (firstName.matches(USER_FIRST_NAME_EN_REGEX)
                && secondName.matches(USER_SECOND_NAME_EN_REGEX))) {
            validationMap.put("incorrectFirstName", false);
            validationMap.put("incorrectSecondName", false);
        }
        if (newParams.get(USER_MOBILE_PHONE).matches(USER_MOBILE_REGEX)) {
            validationMap.put("incorrectMobilePhone", false);
        }
        return validationMap;
    }
}
