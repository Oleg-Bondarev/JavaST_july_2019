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
 * Sorting lexemes by entering character.
 * */
public class SortLexemesByCharacterEntryTest {
    /**
    * @return data for test.
    */
    @DataProvider(name = "DataForSortText")
    public Object[][] testSortProvider() {
        Component component1 = new TextComponent();
        Component component2 = new TextComponent();
        Component component3 = new TextComponent();
        Component component4 = new TextComponent();

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

        textParser.parse(component1, "\tZene not dene attention to him."
                + " Ebe eae.");
        textParser.parse(component2, "");
        textParser.parse(component3, "CCC BBB AAA, DDD. EEE,"
                + " FFF GGG HHH.");
        SortSpecification sorter = new SortLexemesByCharacterEntry('e');
        sorter.sort(component1);
        sorter.sort(component2);
        sorter.sort(component3);
        return new Object[][] {
                {component1, "\tZene dene attention him. not to eae. Ebe"},
                {component2, "\t"},
                {component3, "\tAAA, BBB CCC DDD. EEE, FFF GGG HHH."}
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
