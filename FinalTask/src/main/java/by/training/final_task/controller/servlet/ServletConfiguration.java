package by.training.final_task.controller.servlet;

import by.training.final_task.service.validator.PropertyValidator;
import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ResourceBundle;

public final class ServletConfiguration {
    private static final Logger LOGGER = LogManager.getLogger();

    private String dbDriverClass;
    private String dbURL;
    private String dbUserLogin;
    private String dbUserPassword;
    private int dbPoolStartSize;
    private int dbPoolMaxSize;
    private int dbPoolCheckConnectionTimeout;

    //TODO what to do with exception?
    public ServletConfiguration() /*throws ControllerException*/ {
        ResourceBundle resourceBundle = ResourceBundle
                .getBundle("servlet_configuration");
        readProperties(resourceBundle);
    }

    public String getDbDriverClass() {
        return dbDriverClass;
    }

    public String getDbURL() {
        return dbURL;
    }

    public String getDbUserLogin() {
        return dbUserLogin;
    }

    public String getDbUserPassword() {
        return dbUserPassword;
    }

    public int getDbPoolStartSize() {
        return dbPoolStartSize;
    }

    public int getDbPoolMaxSize() {
        return dbPoolMaxSize;
    }

    public int getDbPoolCheckConnectionTimeout() {
        return dbPoolCheckConnectionTimeout;
    }

    private void readProperties(final ResourceBundle resourceBundle)
        /*throws ControllerException*/ {
        int startSize = 0;
        int maxSize = 0;
        int timeout = 0;
        try {
            startSize = Integer.parseInt(resourceBundle
                    .getString("dbPoolStartSize"));
            maxSize = Integer.parseInt(resourceBundle
                    .getString("dbPoolMaxSize"));
            timeout = Integer.parseInt(resourceBundle
                    .getString("dbPoolCheckTimeout"));
        } catch (ParseException newE) {
            LOGGER.log(Level.ERROR, "Pool size and timeout checking" +
                    " parameter must be an integer numbers.");
            //throw new ControllerException(newE.getMessage(), newE);
        }
        //надо ли через фабрику какую получать объект валидатора?
        if (PropertyValidator.isValidIntegerPropParameters(startSize, maxSize,
                timeout)) {
            dbDriverClass = resourceBundle.getString("dbDriverClass");
            dbURL = resourceBundle.getString("dbURl");
            dbUserLogin = resourceBundle.getString("dbUser");
            dbUserPassword = resourceBundle.getString("dbPassword");
            dbPoolStartSize = startSize;
            dbPoolMaxSize = maxSize;
            dbPoolCheckConnectionTimeout = timeout;
        } else {
            LOGGER.log(Level.ERROR, "Incorrect integer values: " +
                            "start pool size={}, max pool size={}, timeout={}",
                    dbPoolStartSize, dbPoolMaxSize,
                    dbPoolCheckConnectionTimeout);
        }
    }
}
