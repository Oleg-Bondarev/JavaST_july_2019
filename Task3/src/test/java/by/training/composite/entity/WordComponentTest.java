package by.training.composite.entity;

import by.training.composite.service.parser.AbstractParse;
import by.training.composite.service.parser.SymbolParser;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * Word compose test.
 * */
public class WordComponentTest {
    /**
     * @return two-dimension array.
     * */
    @DataProvider(name = "DataForComposeTest")
    public Object[][] testComposeMethod() {
        AbstractParse parse = new SymbolParser();
        Component component = new WordComponent();
        Component component1 = new WordComponent();
        Component component2 = new WordComponent();
        parse.parse(component, "something");
        parse.parse(component1, "");
        parse.parse(component2, "jey-jey");
        return new Object[][] {
                {component, "something"},
                {component1, ""},
                {component2, "jey-jey"},
        };
    }
    /**
     * Testing compose for symbol type.
     * @param component -component.
     * @param expect -string.
     * */
    @Test(dataProvider = "DataForComposeTest", groups = {"Compose group"})
    public void testCompose(final Component component, final String expect) {
        String actual = component.compose();
        assertEquals(actual, expect);
    }
}
