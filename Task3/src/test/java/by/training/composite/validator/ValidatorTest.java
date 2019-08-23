package by.training.composite.validator;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;
import static org.testng.Assert.assertFalse;

/**
 * Validator test.
 * */
public class ValidatorTest {
    /**
     * Negative data provider for check input character.
     * @return array.
     * */
    @DataProvider(name = "NegativeTestForInputCharacter")
    public Object[] createNegativeForValidateStr() {
        return new String[] {"", "addw"};
    }
    /**
     * @param str -test information.
     * */
    @Test(dataProvider = "NegativeTestForInputCharacter",
                        groups = {"Validator group"})
    public void testNegativeCheck(final String str) {
        boolean actual = Validator.isCorrectInputCharacter(str);
        assertFalse(actual);
    }
    /**
     * positive test.
     * */
    @Test(description = "positive test", groups = {"Validator group"})
    public void testPositiveCheck() {
        String str = "e";
        boolean actual = Validator.isCorrectInputCharacter(str);
        assertTrue(actual);
    }
}
