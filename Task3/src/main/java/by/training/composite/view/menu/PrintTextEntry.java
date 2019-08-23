package by.training.composite.view.menu;

import by.training.composite.controller.Command;
import by.training.composite.controller.command.PrintText;

/**
 * Print text entry.
 * */
public class PrintTextEntry extends MenuEntry {
    /**
     * Constructor.
     * @param newTitle -new title.
     * */
    public PrintTextEntry(final String newTitle) {
        super(newTitle);
    }
    /**
     * Run method.
     * */
    @Override
    public void run() {
        Command command = new PrintText();
        command.execute();
    }
}
