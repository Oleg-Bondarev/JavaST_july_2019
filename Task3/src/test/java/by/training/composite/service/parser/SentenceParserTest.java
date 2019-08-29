package by.training.composite.service.parser;

import by.training.composite.entity.Component;
import by.training.composite.entity.ParagraphComponent;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * Paragraph parsing test.
 * */
public class SentenceParserTest {
    /**
     * @return active component and expected component.
     */
    @DataProvider(name = "ParserDataProvider")
    public Object[][] testParseProvider() {
        Component text = new ParagraphComponent();
        SentenceParser sentenceParser = new SentenceParser();
        LexemeParser lexemeParser = new LexemeParser();
        WordParser wordParser = new WordParser();
        SymbolParser symbolParser = new SymbolParser();

        sentenceParser.setNextParser(lexemeParser);
        lexemeParser.setNextParser(wordParser);
        wordParser.setNextParser(symbolParser);

        sentenceParser.parse(text, "First sent... Second sent."
                + " Third sent, Ok!");
        return new Object[][]{
                {text.getChild(0), "First sent..."},
                {text.getChild(1), "Second sent."},
                {text.getChild(2), "Third sent, Ok!"}
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
