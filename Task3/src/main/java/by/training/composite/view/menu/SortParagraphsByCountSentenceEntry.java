package by.training.composite.view.menu;

import by.training.composite.controller.Command;
import by.training.composite.controller.command.SortParagraphsByCountOfSentence;

/**
 * Sort paragraphs by count sentences entry.
 * */
public class SortParagraphsByCountSentenceEntry extends MenuEntry {
    /**
     * Constructor.
     * @param newTitle -new title.
     * */
    SortParagraphsByCountSentenceEntry(final String newTitle) {
        super(newTitle);
    }
    /**
     * Run method.
     * */
    @Override
    public void run() {
        Command command = new SortParagraphsByCountOfSentence();
        command.execute();
    }
}
