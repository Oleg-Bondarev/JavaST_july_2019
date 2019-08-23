package by.training.composite.service.localisation;

import by.training.composite.entity.ResourceManager;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * Localisation handler.
 * */
public class LocalisationHandler {
    /**
     * Map.
     * Key -> language.
     * Value -> locale.
     * */
    private static final Map<String, Locale> MAP_OF_LOCALES = new HashMap<>();
    static {
        MAP_OF_LOCALES.put("english", new Locale("en", "US"));
        MAP_OF_LOCALES.put("russian", new Locale("ru", "RU"));
        MAP_OF_LOCALES.put("belorussian",
                new Locale("be", "BY"));
    }
    /**
     * Menu.
     * @param key -locale.
     * */
    public void locale(final String key) {
        Locale locale = MAP_OF_LOCALES.get(key);
        ResourceManager resourceManager = ResourceManager.getInstance();
        resourceManager.changeLocale(locale);
    }
}
