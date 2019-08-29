package by.training.composite.service.parser;

import by.training.composite.entity.Component;
import by.training.composite.entity.LexemeComponent;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * Word parsing test.
 * */
public class WordParserTest {
    /**
     * @return active component and expected component.
     */
    @DataProvider(name = "ParserDataProvider")
    public Object[][] testParseProvider() {
        Component lexeme1 = new LexemeComponent();
        Component lexeme2 = new LexemeComponent();
        Component lexeme3 = new LexemeComponent();
        Component lexeme4 = new LexemeComponent();
        Component lexeme5 = new LexemeComponent();
        Component lexeme6 = new LexemeComponent();
        WordParser wordParser = new WordParser();
        SymbolParser symbolParser = new SymbolParser();

        wordParser.setNextParser(symbolParser);

        wordParser.parse(lexeme1, "Word");
        wordParser.parse(lexeme2, "word");
        wordParser.parse(lexeme3, "Ok,");
        wordParser.parse(lexeme4, "!");
        wordParser.parse(lexeme5, "...");
        wordParser.parse(lexeme6, "?!");
        return new Object[][]{
                {lexeme1.getChild(0), "Word"},
                {lexeme2.getChild(0), "word"},
                {lexeme3.getChild(0), "Ok"},
                {lexeme3.getChild(1), ","},
                {lexeme4.getChild(0), "!"},
                {lexeme5.getChild(0), "."},
                {lexeme5.getChild(1), "."},
                {lexeme5.getChild(2), "."},
                {lexeme6.getChild(0), "?"},
                {lexeme6.getChild(1), "!"},
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
