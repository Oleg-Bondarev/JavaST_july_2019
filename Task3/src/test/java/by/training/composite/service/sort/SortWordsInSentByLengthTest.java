package by.training.composite.service.sort;

import by.training.composite.entity.Component;
import by.training.composite.entity.TextComponent;
import by.training.composite.service.interfaces.SortSpecification;
import by.training.composite.service.parser.TextParser;
import by.training.composite.service.parser.ParagraphParser;
import by.training.composite.service.parser.SentenceParser;
import by.training.composite.service.parser.LexemeParser;
import by.training.composite.service.parser.WordParser;
import by.training.composite.service.parser.SymbolParser;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * Sorting words test.
 * */
public class SortWordsInSentByLengthTest {
    /**
     * @return two-dimension array.
     * */
    @DataProvider(name = "DataForSortText")
    public Object[][] testComposeMethod() {
        TextComponent component1 = new TextComponent();
        TextComponent component2 = new TextComponent();
        TextComponent component3 = new TextComponent();

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

        textParser.parse(component1, "\tDo not pay attention to him."
                + " Ebe eae.\n\tSome english text.");
        textParser.parse(component2, "");
        textParser.parse(component3, "Word");
        SortSpecification sorter = new SortWordsInSentByLength();
        sorter.sort(component1);
        sorter.sort(component2);
        sorter.sort(component3);
        return new Object[][] {
                {component1, "\tDo to not pay him. attention Ebe eae.\n\t"
                        + "Some text. english"},
                {component2, "\t"},
                {component3, "\tWord"}
        };
    }

    /**
     * @param component actual component.
     * @param expect result string.
     */
    @Test(dataProvider = "DataForSortText", groups = {"Sorter group"})
    public void testSort(final Component component, final String expect) {
        String actual = component.compose();
        assertEquals(actual, expect);
    }
}
