package by.training.composite.entity;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Resource manager.
 * */
public enum ResourceManager {
    /**
     * Instance.
     * */
    INSTANCE;
    /**
     * Resource bundle.
     * */
    private ResourceBundle resourceBundle;
    /**
     * Resource name.
     * */
    private final String resource = "property.text";
    /**
     * Constructor.
     * */
    ResourceManager() {
        resourceBundle
                = ResourceBundle.getBundle(resource,  new Locale("en", "US"));
    }
    /**
     * Changing locale.
     * @param newLocale -new locale.
     * */
    public void changeLocale(final Locale newLocale) {
        resourceBundle = ResourceBundle.getBundle(resource, newLocale);
    }
    /**
     * Getter.
     * @param key -key.
     * @return string.
     * */
    public String getString(final String key) {
        return resourceBundle.getString(key);
    }
}
