package by.training.composite.entity;

import by.training.composite.service.parser.LexemeParser;
import by.training.composite.service.parser.WordParser;
import by.training.composite.service.parser.SymbolParser;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * Lexeme compose test.
 * */
public class LexemeComponentTest {
    /**
     * @return two-dimension array.
     * */
    @DataProvider(name = "DataForComposeTest")
    public Object[][] testComposeMethod() {
        LexemeParser lexemeParser = new LexemeParser();
        WordParser wordParser = new WordParser();
        SymbolParser symbolParser = new SymbolParser();

        lexemeParser.setNextParser(wordParser);
        wordParser.setNextParser(symbolParser);

        Component component = new SentenceComponent();
        Component component1 = new SentenceComponent();
        Component component2 = new SentenceComponent();
        Component component3 = new SentenceComponent();
        lexemeParser.parse(component, "Do not pay attention to him.");
        lexemeParser.parse(component1, "");
        lexemeParser.parse(component2, "First...");
        lexemeParser.parse(component3, "Simple sentence.");
        return new Object[][] {
                {component, "Do not pay attention to him."},
                {component1, ""},
                {component2, "First..."},
                {component3, "Simple sentence."}
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
