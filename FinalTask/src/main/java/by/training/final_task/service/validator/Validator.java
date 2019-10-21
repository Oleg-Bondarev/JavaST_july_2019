package by.training.final_task.service.validator;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Validator {
    private static final Logger LOGGER = LogManager.getLogger();
    private Map<String, Boolean> validationMap = new HashMap<>();

    public Map<String, Boolean> initValidationMap(final String[] params) {
        clearMap();
        for (String param: params) {
            validationMap.put(param, true);
        }
        return validationMap;
    }

    private void clearMap() {
        validationMap.clear();
    }

    public void checkPotentialAttack(final List<String> newParameters) {
        for (String temp : newParameters) {
            if (temp.contains("'")) {
                LOGGER.log(Level.WARN, "Potential SQL injection attack: "
                        + "{}", temp);
            }
            if (temp.contains("<script>") || temp.contains("</script>")) {
                LOGGER.log(Level.WARN, "Potential JS script: {}", temp);
            }
        }
    }

    public static boolean validateCompanyName(final String name) {
        final String companyNameRegex = "^[a-zA-Zа-яА-Я0-9-\" ]+$";
        return name.matches(companyNameRegex);
    }
}
