package by.training.composite.service.parser;

import by.training.composite.entity.Component;
import by.training.composite.entity.SymbolComponent;
import by.training.composite.entity.WordComponent;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * Test for symbol parser.
 * */
public class SymbolParserTest {
    /**
     * @return active component and expected component.
     */
    @DataProvider(name = "ParserDataProvider")
    public Object[][] testParseProvider() {
        Component word = new WordComponent();
        Component result = new WordComponent();
        Component word1 = new WordComponent();
        Component result1 = new WordComponent();

        SymbolParser parser = new SymbolParser();
        parser.parse(word, "some");

        result.add(new SymbolComponent('s'));
        result.add(new SymbolComponent('o'));
        result.add(new SymbolComponent('m'));
        result.add(new SymbolComponent('e'));

        parser.parse(word1, "");
        return new Object[][]{
                {result.getChild(0), new SymbolComponent('s')},
                {result.getChild(1), new SymbolComponent('o')},
                {result.getChild(2), new SymbolComponent('m')},
                {result.getChild(3), new SymbolComponent('e')},
                {word1, result1}
        };
    }
    /**
     * @param component   active component.
     * @param expected expected component.
     */
    @Test(dataProvider = "ParserDataProvider", groups = {"Parser group"})
    public void testParse(final Component component, final Component expected) {
        String actual = component.compose();
        assertEquals(actual, expected.compose());
    }
}
