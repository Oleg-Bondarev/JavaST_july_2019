package by.training.composite.service.parser;

import by.training.composite.entity.Component;
import by.training.composite.entity.TextComponent;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * Text parsing test.
 * */
public class ParagraphParserTest {
    /**
     * @return active component and expected component.
     */
    @DataProvider(name = "ParserDataProvider")
    public Object[][] testParseProvider() {
        Component text = new TextComponent();
        TextParser textParser = new TextParser();
        ParagraphParser paragraphParser = new ParagraphParser();
        SentenceParser sentenceParser = new SentenceParser();
        LexemeParser lexemeParser = new LexemeParser();
        WordParser wordParser = new WordParser();
        SymbolParser symbolParser = new SymbolParser();

        textParser.setNextParser(paragraphParser);
        paragraphParser.setNextParser(sentenceParser);
        sentenceParser.setNextParser(lexemeParser);
        lexemeParser.setNextParser(wordParser);
        wordParser.setNextParser(symbolParser);

        textParser.parse(text, "\tFirst sent... Second sent.\n"
                + "\tFirst-second?!      Frrreee!\n"
                + "\tThird-first. Some sent, end of par.\n"
                + "\t");
        return new Object[][]{
                {text.getChild(0), "First sent... Second sent."},
                {text.getChild(1), "First-second?! Frrreee!"},
                {text.getChild(2), "Third-first. Some sent, end of par."}
        };
    }
    /**
     * @param component   active component.
     * @param expect expected component.
     */
    @Test(dataProvider = "ParserDataProvider", groups = {"Parser group"})
    public void testParse(final Component component, final String expect) {
        String actual = component.compose();
        assertEquals(actual, expect);
    }
}
