package by.training.final_task.service.parser;

import by.training.final_task.action.Action;
import by.training.final_task.entity.User;
import by.training.final_task.service.validator.UserParametersValidator;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class UserFormParser extends FormParser<User> {

    private static final int USER_LOGIN = 0;
    private static final int USER_PASSWORD = 1;
    private static final int USER_EMAIL = 2;
    private static final int USER_FIRST_NAME = 3;
    private static final int USER_SECOND_NAME = 4;
    private static final int USER_MOBILE_PHONE = 5;

    @Override
    public User parse(final Action newAction,
                      final List<String> newUserParameters)
            throws InvalidFormDataException {
        if (!newUserParameters.isEmpty() && !newUserParameters.contains(null)
                && !newUserParameters.contains("")) {
            UserParametersValidator validator = new UserParametersValidator();
            Map<String, Boolean> validationMap = validator
                    .validate(newUserParameters);
            for (Map.Entry<String, Boolean> entry : validationMap.entrySet()) {
                if (Boolean.TRUE.equals(entry.getValue())) {
                    throw new InvalidFormDataException(entry.getKey());
                }
            }
            String login = newUserParameters.get(USER_LOGIN);
            String password = newUserParameters.get(USER_PASSWORD);
            String email = newUserParameters.get(USER_EMAIL);
            String firstName = newUserParameters.get(USER_FIRST_NAME);
            String secondName = newUserParameters.get(USER_SECOND_NAME);
            Integer mobilePhone = Integer.parseInt(newUserParameters
                    .get(USER_MOBILE_PHONE));
            LocalDate registrationDate = LocalDate.now();
            return new User(0, login, password, null, email,
                    null, firstName, secondName, mobilePhone,
                    registrationDate, false);
        }
        throw new InvalidFormDataException("fillAllFields");
    }
}
