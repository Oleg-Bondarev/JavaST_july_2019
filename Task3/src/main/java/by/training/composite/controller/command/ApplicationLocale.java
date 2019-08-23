package by.training.composite.controller.command;

import by.training.composite.controller.Command;
import by.training.composite.service.localisation.LocalisationHandler;

/**
 * Locale of the program.
 * */
public class ApplicationLocale implements Command {
    /**
     * Locale.
     * */
    private String locale;
    /**
     * Constructor.
     * @param newLocale -new locale.
     * */
    public ApplicationLocale(final String newLocale) {
        this.locale = newLocale;
    }
    /**
     * Execute method.
     * */
    @Override
    public void execute() {
        LocalisationHandler localisationHandler = new LocalisationHandler();
        localisationHandler.locale(locale);
    }
}
