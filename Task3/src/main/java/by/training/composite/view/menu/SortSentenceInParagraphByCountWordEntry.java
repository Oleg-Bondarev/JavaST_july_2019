package by.training.composite.view.menu;

import by.training.composite.controller.Command;
import by.training.composite.controller.command.SortSentencesInParagraphByCountWord;

/**
 * Sort entry.
 * */
public class SortSentenceInParagraphByCountWordEntry extends MenuEntry {
    /**
     * Constructor.
     * @param newTitle -new title.
     * */
    SortSentenceInParagraphByCountWordEntry(final String newTitle) {
        super(newTitle);
    }
    /**
     * Run method.
     * */
    @Override
    public void run() {
        Command command = new SortSentencesInParagraphByCountWord();
        command.execute();
    }
}
