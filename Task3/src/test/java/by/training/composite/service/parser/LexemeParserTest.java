package by.training.composite.service.parser;

import by.training.composite.entity.Component;
import by.training.composite.entity.SentenceComponent;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * Sentence parsing test.
 * */
public class LexemeParserTest {
    /**
     * @return active component and expected component.
     */
    @DataProvider(name = "ParserDataProvider")
    public Object[][] testParseProvider() {
        Component text = new SentenceComponent();
        LexemeParser lexemeParser = new LexemeParser();
        WordParser wordParser = new WordParser();
        SymbolParser symbolParser = new SymbolParser();

        lexemeParser.setNextParser(wordParser);
        wordParser.setNextParser(symbolParser);

        lexemeParser.parse(text, "Third sent, Ok!");
        return new Object[][]{
                {text.getChild(0), "Third"},
                {text.getChild(1), "sent,"},
                {text.getChild(2), "Ok!"}
        };
    }

    /**
     * @param component -active component.
     * @param expect    -expected component.
     */
    @Test(dataProvider = "ParserDataProvider", groups = {"Parser group"})
    public void testParse(final Component component, final String expect) {
        String actual = component.compose();
        assertEquals(actual, expect);
    }
}
