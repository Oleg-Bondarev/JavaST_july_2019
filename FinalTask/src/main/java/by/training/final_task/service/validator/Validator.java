package by.training.final_task.service.validator;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Represent validation methods.
 */
public class Validator {
    /**
     * Class logger.
     */
    private static final Logger LOGGER = LogManager.getLogger();
    /**
     * Map for recognize invalid parameters.
     */
    private Map<String, Boolean> validationMap = new HashMap<>();

    /**
     * Initialize validation map. At the beginning it is assumed that all
     * fields are incorrect.
     *
     * @param params array of warning messages for fields.
     * @return map of warnings.
     */
    public Map<String, Boolean> initValidationMap(final String[] params) {
        clearMap();
        for (String param : params) {
            validationMap.put(param, true);
        }
        return validationMap;
    }

    /**
     * Clearing map of warnings.
     */
    private void clearMap() {
        validationMap.clear();
    }

    /**
     * Method that calls other methods for check potential XSS and Injection
     * attack and logging if there were attacks.
     *
     * @param newParameters parameters for check potential attack.
     */
    public void checkPotentialAttack(final List<String> newParameters) {
        for (String temp : newParameters) {
            if (isPotentialSQLInjection(temp)) {
                LOGGER.log(Level.WARN, "Potential SQL injection attack: "
                        + "{}", temp);
            }
            if (isPotentialXSS(temp)) {
                LOGGER.log(Level.WARN, "Potential JS script: {}", temp);
            }
        }
    }

    /**
     * Recognize SQL Injections.
     *
     * @param newParameter string parameter for check potential attack.
     * @return true if contains SQL Injection, false - otherwise.
     */
    private boolean isPotentialSQLInjection(final String newParameter) {
        return newParameter.contains("'");
    }

    /**
     * Recognize XSS.
     *
     * @param newParameter string parameter for check potential attack.
     * @return true if contains XSS, false - otherwise.
     */
    private boolean isPotentialXSS(final String newParameter) {
        return (newParameter.contains("<script>")
                || newParameter.contains("</script>"));
    }

    /**
     * Validation company name.
     *
     * @param newName input string parameter.
     * @return true if valid company name, false - otherwise.
     */
    public boolean validateCompanyName(final String newName) {
        return newName.matches(CompanyParametersValidator
                .REGEX_FOR_COMPANY_NAME);
    }

    /**
     * Validation company name.
     *
     * @param newLogin input string parameter.
     * @return true if login is valid, false - otherwise.
     */
    public boolean validateUserLogin(final String newLogin) {
        return newLogin.matches(UserParametersValidator.USER_LOGIN_REGEX);
    }

    /**
     * Validation company name.
     *
     * @param newPrice input string parameter.
     * @return true if coupon price is valid, false - otherwise.
     */
    public boolean validateCouponPrice(final String newPrice) {
        final String defaultMinPrice = "0.00";
        if (!isPotentialXSS(newPrice) && !isPotentialSQLInjection(newPrice)) {
            if ((newPrice.equals(defaultMinPrice)) || newPrice.matches(
                    CouponParametersValidator.REGEX_FOR_COUPON_PRICE)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Validation company name.
     *
     * @param minRange minimum price parameter.
     * @param maxRange maximum price parameter.
     * @return true if range is correct, false - otherwise.
     */
    public boolean validatePriceRange(final String minRange,
                                      final String maxRange) {
        if (validateCouponPrice(minRange) && validateCouponPrice(maxRange)) {
            BigDecimal minPrice = new BigDecimal(minRange);
            BigDecimal maxPrice = new BigDecimal(maxRange);
            if (minPrice.compareTo(maxPrice) < 1) {
                return true;
            }
        }
        return false;
    }
    /**
     * Validate id parameter in get request.
     * @param newId id from get request.
     * @return true if it is a digit, false - otherwise.
     * */
    public boolean validateIdParameter(final String newId) {
        final String regex = "^[\\d]+$";
        return newId.matches(regex);
    }
}
