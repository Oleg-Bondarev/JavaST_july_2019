package by.training.final_task.service.validator;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserParametersValidator {
    //TODO нужно ли про валидации логгировать возможные случаи sql injections?
    private static final Logger LOGGER = LogManager.getLogger();

    private static Map<String, Boolean> validationMap = new HashMap<>();

    private static final int USER_LOGIN = 0;
    private static final int USER_PASSWORD = 1;
    private static final int USER_EMAIL = 2;
    private static final int USER_FIRST_NAME = 3;
    private static final int USER_SECOND_NAME = 4;
    private static final int USER_MOBILE_PHONE = 5;
    private static final String USER_LOGIN_REGEX = "^[a-zA-Z0-9]{4,16}$";
    private static final String USER_PASSWORD_REGEX = "^[a-zA-Z0-9]{8,16}$";
    private static final String USER_EMAIL_REGEX = "^([a-z0-9_-]+\\.)*[a-z0-9_-]+@[a-z0-9_-]+(\\.[a-z0-9_-]+)*\\.[a-z]{2,6}$";
    private static final String USER_FIRST_NAME_RU_REGEX = "^[а-яА-Я-]{2,10}$";
    private static final String USER_SECOND_NAME_RU_REGEX = "^[а-яА-Я-]{2,20}$";
    private static final String USER_FIRST_NAME_EN_REGEX = "^[a-zA-Z-]{2,10}$";
    private static final String USER_SECOND_NAME_EN_REGEX = "^[a-zA-Z-]{2,20}$";
    private static final String USER_MOBILE_REGEX = "^((25)|(29)|(33)|(44))([0-9]{7}$)";

    private static void InitMap() {
        validationMap.put("incorrectLogin", true);
        validationMap.put("incorrectPassword", true);
        validationMap.put("incorrectEmail", true);
        validationMap.put("incorrectFirstName", true);
        validationMap.put("incorrectSecondName", true);
        validationMap.put("incorrectMobilePhone", true);
    }

    public static Map<String, Boolean> ValidateUserParameters(final List<String> newParams) {
        for (String temp : newParams) {
            if (temp.contains("'")) {
                LOGGER.log(Level.WARN, "Potential SQL injection attack: "
                        + "{}", temp);
            }
        }

        InitMap();

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
            validationMap.put("incorrectMobilePhone",  false);
        }
        return validationMap;
    }
}
