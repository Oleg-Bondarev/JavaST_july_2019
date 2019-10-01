package by.training.final_task.controller.servlet;

import by.training.final_task.controller.ControllerException;
import by.training.final_task.service.validator.PropertyValidator;
import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ResourceBundle;

public class ServletConfiguration {
    private static final Logger LOGGER = LogManager.getLogger();
    private String loggingFile;
    private Level loggingLevel;
    private String loggingFormat;
    private String dbDriverClass;
    private String dbURL;
    private String dbUserLogin;
    private String dbUserPassword;
    private int dbPoolStartSize;
    private int dbPoolMaxSize;
    private int dbPoolCheckConnectionTimeout;

    public ServletConfiguration() throws ControllerException {
        ResourceBundle resourceBundle = ResourceBundle
                .getBundle("servlet_configuration");
        readProperties(resourceBundle);
    }

    public String getLoggingFile() {
        return loggingFile;
    }

    public Level getLoggingLevel() {
        return loggingLevel;
    }

    public String getLoggingFormat() {
        return loggingFormat;
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
            throws ControllerException {
        int startSize;
        int maxSize;
        int timeout;
        try {
            startSize = Integer.parseInt(resourceBundle.getString("dbPoolStartSize"));
            maxSize = Integer.parseInt(resourceBundle.getString("dbPoolMaxSize"));
            timeout = Integer.parseInt(resourceBundle.getString("dbPoolCheckTimeout"));
        } catch (ParseException newE) {
            LOGGER.log(Level.ERROR, "Pool size and timeout checking" +
                    " parameter must be an integer numbers.");
            throw new ControllerException(newE.getMessage(), newE);
        }
        //надо ли через фабрику какую получать объект валидатора?
        if (PropertyValidator.isValidIntegerPropParameters(startSize, maxSize, timeout)) {
            loggingFile =  resourceBundle.getString("logFilename");
            loggingLevel = Level.valueOf(resourceBundle.getString("logLevel"));
            loggingFormat = resourceBundle.getString("logFromat");
            dbDriverClass =  resourceBundle.getString("dbDriverClass");
            dbURL =  resourceBundle.getString("dbURl");
            dbUserLogin =  resourceBundle.getString("dbUser");
            dbPoolStartSize = startSize;
            dbPoolMaxSize = maxSize;
            dbPoolCheckConnectionTimeout = timeout;
        } else {
            LOGGER.log(Level.ERROR, "Incorrect integer values: " +
                "start pool size={}, max pool size={}, timeout={}",
                dbPoolStartSize, dbPoolMaxSize, dbPoolCheckConnectionTimeout);
        }
    }
}
