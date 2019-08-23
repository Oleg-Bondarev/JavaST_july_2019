package by.training.composite.service.sort;

import by.training.composite.entity.Component;
import by.training.composite.entity.TextComponent;
import by.training.composite.service.interfaces.SortSpecification;
import by.training.composite.service.parser.*;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * Testing paragraphs sorting.
 * */
public class SortParByCountSentTest {
    /**
     * @return data for test.
     */
    @DataProvider(name = "DataForSortText")
    public Object[][] testSortProvider() {
        Component component1 = new TextComponent();
        Component component2 = new TextComponent();
        Component component3 = new TextComponent();

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

        textParser.parse(component1, "\tOne. Two?\n\tSome text... "
                + "Really?!\n\tNew top paragraph!\n");
        textParser.parse(component2, "");
        textParser.parse(component3, "Word");
        SortSpecification sorter = new SortParByCountSent();
        sorter.sort(component1);
        sorter.sort(component2);
        sorter.sort(component3);
        return new Object[][] {
                {component1, "\tNew top paragraph!\n\tOne. Two?"
                        + "\n\tSome text... Really?!"},
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
