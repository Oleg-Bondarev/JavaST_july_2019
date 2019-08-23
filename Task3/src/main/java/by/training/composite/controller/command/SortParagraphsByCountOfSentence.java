package by.training.composite.controller.command;

import by.training.composite.controller.Command;
import by.training.composite.entity.Component;
import by.training.composite.entity.TextStorage;
import by.training.composite.service.interfaces.SortSpecification;
import by.training.composite.service.sort.SortParByCountSent;

/**
 * Sorting paragraphs by count of sentence.
 * */
public class SortParagraphsByCountOfSentence implements Command {
    /**
     * Execute method.
     * */
    @Override
    public void execute() {
        TextStorage textStorage = TextStorage.getInstance();
        Component component = textStorage.getTextComponent();
        SortSpecification sortParagraphs = new SortParByCountSent();
        sortParagraphs.sort(component);
    }
}
