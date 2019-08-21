package by.training.composite.service.properties;

import by.training.composite.service.ServiceException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Represent reading properties from property file.
 * */
public final class PropertiesReader {
    /**
     * Instance.
     * */
    private static final PropertiesReader INSTANCE
            = new PropertiesReader();
    /**
     * Constructor.
     * */
    private PropertiesReader() { }
    /**
     * @return instance.
     * */
    public static PropertiesReader getPropertiesReader() {
        return INSTANCE;
    }
    /**
     * @param propertyFile -prop. file.
     * @param key -prop. key.
     * @return properties.
     * @throws ServiceException -if have problems.
     * */
    public static String takeProperty(final String propertyFile,
                                  final String key) throws ServiceException {
        ClassLoader classLoader = PropertiesReader.class.getClassLoader();
        Properties property = new Properties();
        try (InputStream inputStream = classLoader
                .getResourceAsStream(propertyFile)) {
            if (inputStream == null) {
                throw new ServiceException("Can't find file " + propertyFile);
            }
            property.load(inputStream);
        } catch (IOException e) {
            throw new ServiceException("Exception during reading file "
                    + propertyFile, e);
        }
        return property.getProperty(key);
    }
}
