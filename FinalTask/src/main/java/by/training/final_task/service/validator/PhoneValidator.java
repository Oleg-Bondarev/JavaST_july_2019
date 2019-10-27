package by.training.final_task.service.validator;

import java.util.ArrayList;
import java.util.List;

public class PhoneValidator extends Validator {
    private static final String REGEX_FOR_PHONE =
            "^((25)|(29)|(33)|(44))([0-9]{7}$)";

    public static boolean validate(final String newPhone) {
        Validator validator = new Validator();
        List<String> params = new ArrayList<>();
        params.add(newPhone);
        validator.checkPotentialAttack(params);
        return newPhone.matches(REGEX_FOR_PHONE);
    }
}
