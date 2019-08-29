package by.training.composite.entity;

import by.training.composite.service.parser.SymbolParser;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * Punctuation compose test.
 * */
public class PunctuationComponentTest {
    /**
     * Data provider for test.
     * @return two-dimension array.
     * */
    @DataProvider(name = "DataForComposeTest")
    public Object[][] testComposeMethod() {
        SymbolParser parser = new SymbolParser();
        Component component = new PunctuationComponent();
        Component component1 = new PunctuationComponent();
        Component component2 = new PunctuationComponent();
        Component component3 = new PunctuationComponent();
        Component component4 = new PunctuationComponent();
        Component component5 = new PunctuationComponent();
        Component component6 = new PunctuationComponent();
        parser.parse(component, "...");
        parser.parse(component1, "?!");
        parser.parse(component2, "?");
        parser.parse(component3, "!");
        parser.parse(component4, ".");
        parser.parse(component5, ",");
        parser.parse(component6, "");
        return new Object[][]{
                {component, "..."},
                {component1, "?!"},
                {component2, "?"},
                {component3, "!"},
                {component4, "."},
                {component5, ","},
                {component6, ""}
        };
    }
    /**
     * Testing compose for punctuation type.
     * @param component -component.
     * @param expect -string.
     * */
    @Test(dataProvider = "DataForComposeTest", groups = {"Compose group"})
    public void testCompose(final Component component,
                            final String expect) {
        String actual = component.compose();
        assertEquals(actual, expect);
    }
}
