package by.training.final_task.service.validator;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.testng.Assert.*;

public class UserParametersValidatorTest {
    private UserParametersValidator userParametersValidator =
            new UserParametersValidator();
    private Map<String, Boolean> expectMap = new HashMap<>();
    private Map<String, Boolean> actualMap = new HashMap<>();

    @BeforeMethod
    public void clearMap() {
        expectMap.clear();
        actualMap.clear();
    }

    @DataProvider(name = "NegativeTestForFirstUserParameter")
    public Object[] createNegativeForFirstUserParameter() {
        return new String[][] {
                {"Name1'", "1234567890", "test@mail.ru", "Name", "Surname", "293695421"},
                {"<script>Name1</script>",  "1234567890", "test@mail.ru", "Name", "Surname", "293695421"},
                {"admin' or '1'='1",  "1234567890", "test@mail.ru", "Name", "Surname", "293695421"},
                {"Name//",  "1234567890", "test@mail.ru", "Name", "Surname", "293695421"}
        };
    }

    @Test(dataProvider = "NegativeTestForFirstUserParameter", groups = {"Validator group"})
    public void testNegativeValidateFirst(final String[] newStringsArr) {
        List<String> testList = Arrays.asList(newStringsArr);
        expectMap.put("incorrectLogin", true);
        expectMap.put("incorrectPassword", false);
        expectMap.put("incorrectEmail", false);
        expectMap.put("incorrectFirstName", false);
        expectMap.put("incorrectSecondName", false);
        expectMap.put("incorrectMobilePhone", false);

        actualMap = userParametersValidator.validate(testList);
        assertEquals(actualMap, expectMap);
    }

    @DataProvider(name = "NegativeTestForSecondUserParameter")
    public Object[] createNegativeForSecondUserParameter() {
        return new String[][] {
                {"Name1", "123", "test@mail.ru", "Name", "Surname", "293695421"},
                {"Name1", "1234567890123456789", "test@mail.ru", "Name", "Surname", "293695421"},
                {"Name1",  "<script>1234567890</script>", "test@mail.ru", "Name", "Surname", "293695421"},
                {"Name1",  "admin' or '1'='1", "test@mail.ru", "Name", "Surname", "293695421"},
                {"Name1",  "1234567890//", "test@mail.ru", "Name", "Surname", "293695421"}
        };
    }

    @Test(dataProvider = "NegativeTestForSecondUserParameter", groups = {"Validator group"})
    public void testNegativeValidateSecond(final String[] newStringsArr) {
        List<String> testList = Arrays.asList(newStringsArr);
        expectMap.put("incorrectLogin", false);
        expectMap.put("incorrectPassword", true);
        expectMap.put("incorrectEmail", false);
        expectMap.put("incorrectFirstName", false);
        expectMap.put("incorrectSecondName", false);
        expectMap.put("incorrectMobilePhone", false);

        actualMap = userParametersValidator.validate(testList);
        assertEquals(actualMap, expectMap);
    }

    @DataProvider(name = "NegativeTestForThirdUserParameter")
    public Object[] createNegativeForThirdUserParameter() {
        return new String[][] {
                {"Name1", "1234567890", "testmail.rur", "Name", "Surname", "293695421"},
                {"Name1", "1234567890", "@mail.rur", "Name", "Surname", "293695421"},
                {"Name1", "1234567890", "test@.ru", "Name", "Surname", "293695421"},
                {"Name1", "1234567890", "test@mail.", "Name", "Surname", "293695421"},
                {"Name1", "1234567890", "test@mail.ru@", "Name", "Surname", "293695421"},
                {"Name1",  "1234567890", "admin' or '1'='1", "Name", "Surname", "293695421"},
                {"Name1",  "1234567890", "<script>test@mail.ru</script>", "Name", "Surname", "293695421"},
                {"Name1",  "1234567890", "test@mail.ru//", "Name", "Surname", "293695421"}
        };
    }

    @Test(dataProvider = "NegativeTestForThirdUserParameter", groups = {"Validator group"})
    public void testNegativeValidateThird(final String[] newStringsArr) {
        List<String> testList = Arrays.asList(newStringsArr);
        expectMap.put("incorrectLogin", false);
        expectMap.put("incorrectPassword", false);
        expectMap.put("incorrectEmail", true);
        expectMap.put("incorrectFirstName", false);
        expectMap.put("incorrectSecondName", false);
        expectMap.put("incorrectMobilePhone", false);

        actualMap = userParametersValidator.validate(testList);
        assertEquals(actualMap, expectMap);
    }

    @DataProvider(name = "NegativeTestForFourthAndFifthUserParameter")
    public Object[] createNegativeForFourthUserParameter() {
        return new String[][] {
                {"Name1", "1234567890", "test@mail.ru", "Name'", "Surname", "293695421"},
                {"Name1",  "1234567890", "test@mail.ru", "admin' or '1'='1", "Surname", "293695421"},
                {"Name1",  "1234567890", "test@mail.ru", "<script>Name</script>", "Surname", "293695421"},
                {"Name1",  "1234567890", "test@mail.ru", "Name//", "Surname", "293695421"}
        };
    }

    @Test(dataProvider = "NegativeTestForFourthAndFifthUserParameter", groups = {"Validator group"})
    public void testNegativeValidateFourthAndFifth(final String[] newStringsArr) {
        List<String> testList = Arrays.asList(newStringsArr);
        expectMap.put("incorrectLogin", false);
        expectMap.put("incorrectPassword", false);
        expectMap.put("incorrectEmail", false);
        expectMap.put("incorrectFirstName", true);
        expectMap.put("incorrectSecondName", true);
        expectMap.put("incorrectMobilePhone", false);

        actualMap = userParametersValidator.validate(testList);
        assertEquals(actualMap, expectMap);
    }
}