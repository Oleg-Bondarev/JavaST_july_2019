package by.training.composite.entity;

import by.training.composite.service.parser.ParagraphParser;
import by.training.composite.service.parser.TextParser;
import by.training.composite.service.parser.SentenceParser;
import by.training.composite.service.parser.LexemeParser;
import by.training.composite.service.parser.WordParser;
import by.training.composite.service.parser.SymbolParser;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * Text compose test.
 * */
public class TextComponentTest {
    /**
     * @return two-dimension array.
     * */
    @DataProvider(name = "DataForComposeTest")
    public Object[][] testComposeMethod() {
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

        TextComponent component = new TextComponent();
        TextComponent component1 = new TextComponent();
        TextComponent component2 = new TextComponent();
        TextComponent component3 = new TextComponent();
        textParser.parse(component, "\tFirst paragraph."
                + "\n\tSecond paragraph...\n\tThird paragraph?!");
        textParser.parse(component1, "");
        textParser.parse(component2, "\tFirs.\n\tSecond?");
        textParser.parse(component3, "Simple sentence.");
        return new Object[][] {
                {component, "\tFirst paragraph.\n\tSecond paragraph...\n\t"
                        + "Third paragraph?!"},
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
