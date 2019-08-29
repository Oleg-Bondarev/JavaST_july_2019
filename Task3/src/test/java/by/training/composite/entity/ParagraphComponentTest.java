package by.training.composite.entity;

import by.training.composite.service.parser.ParagraphParser;
import by.training.composite.service.parser.SentenceParser;
import by.training.composite.service.parser.LexemeParser;
import by.training.composite.service.parser.WordParser;
import by.training.composite.service.parser.SymbolParser;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * Paragraph compose test.
 * */
public class ParagraphComponentTest {
    /**
     * @return two-dimension array.
     * */
    @DataProvider(name = "DataForComposeTest")
    public Object[][] testComposeMethod() {
        ParagraphParser paragraphParser = new ParagraphParser();
        SentenceParser sentenceParser = new SentenceParser();
        LexemeParser lexemeParser = new LexemeParser();
        WordParser wordParser = new WordParser();
        SymbolParser symbolParser = new SymbolParser();

        paragraphParser.setNextParser(sentenceParser);
        sentenceParser.setNextParser(lexemeParser);
        lexemeParser.setNextParser(wordParser);
        wordParser.setNextParser(symbolParser);

        Component component = new TextComponent();
        Component component1 = new TextComponent();
        Component component2 = new TextComponent();
        Component component3 = new TextComponent();
        paragraphParser.parse(component, "    Do not pay attention to him."
                        + " Ebe eae.\n    Some english text.");
        paragraphParser.parse(component1, "");
        paragraphParser.parse(component2, "    Firs.\n    Second?");
        paragraphParser.parse(component3, "Simple sentence.");
        return new Object[][] {
                {component, "\tDo not pay attention to him. Ebe eae.\n\t"
                        + "Some english text."},
                {component1, "\t"},
                {component2, "\tFirs.\n\tSecond?"},
                {component3, "\tSimple sentence."}
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
