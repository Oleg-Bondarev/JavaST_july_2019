package by.training.composite.entity;

import by.training.composite.service.parser.SentenceParser;
import by.training.composite.service.parser.LexemeParser;
import by.training.composite.service.parser.WordParser;
import by.training.composite.service.parser.SymbolParser;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * Sentence compose test.
 * */
public class SentenceComponentTest {
    /**
     * @return two-dimension array.
     * */
    @DataProvider(name = "DataForComposeTest")
    public Object[][] testComposeMethod() {
        SentenceParser sentenceParser = new SentenceParser();
        LexemeParser lexemeParser = new LexemeParser();
        WordParser wordParser = new WordParser();
        SymbolParser symbolParser = new SymbolParser();

        sentenceParser.setNextParser(lexemeParser);
        lexemeParser.setNextParser(wordParser);
        wordParser.setNextParser(symbolParser);

        Component component = new ParagraphComponent();
        Component component1 = new ParagraphComponent();
        Component component2 = new ParagraphComponent();
        Component component3 = new ParagraphComponent();
        sentenceParser.parse(component, "    Do not pay attention to him."
                + " Ebe eae.  Some english text.");
        sentenceParser.parse(component1, "");
        sentenceParser.parse(component2, "    First...  Second?");
        sentenceParser.parse(component3, "Simple sentence.");
        return new Object[][] {
                {component, "Do not pay attention to him. Ebe eae."
                        + " Some english text."},
                {component1, ""},
                {component2, "First... Second?"},
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
