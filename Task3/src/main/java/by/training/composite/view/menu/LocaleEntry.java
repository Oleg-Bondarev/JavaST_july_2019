package by.training.composite.view.menu;

import by.training.composite.controller.command.ApplicationLocale;

/**
 * Locale entry class.
 * */
public class LocaleEntry extends MenuEntry {
    /**
     * Constructor.
     * @param newTitle -new title.
     * */
    public LocaleEntry(final String newTitle) {
        super(newTitle);
    }
    /**
     * Run method.
     * */
    @Override
    public void run() {
        ApplicationLocale applicationLocale
                = new ApplicationLocale(getMenuTitle());
        applicationLocale.execute();
    }
}
