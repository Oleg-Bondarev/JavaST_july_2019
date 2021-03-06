package by.training.composite.controller.command;

import by.training.composite.controller.Command;
import by.training.composite.entity.Component;
import by.training.composite.entity.TextStorage;
import by.training.composite.service.interfaces.FileService;
import by.training.composite.service.ServiceException;
import by.training.composite.service.ServiceFactory;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Loading text from file.
 * */
public class LoadText implements Command {
    /**
     * Service factory.
     * */
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    /**
     * Logger.
     * */
    private static final Logger LOGGER = LogManager.getLogger();
    /**
     * Execute method.
     * */
    @Override
    public void execute() {
        String path = "";
        try {
            path = serviceFactory.getPropertiesReader()
                    .takeProperty("property/config.properties",
                                "inputFile");
        } catch (ServiceException e) {
            LOGGER.log(Level.ERROR, e.getMessage());
            throw new RuntimeException("Can't find file to continue program.");
        }
        FileService fileService = serviceFactory.getFileService();
        TextStorage textStorage = TextStorage.getInstance();
        try {
            String tempStr = fileService.read(path);
            Component component
                    = serviceFactory.getTextTree().createTree(tempStr);
            textStorage.setTextComponent(component);
        } catch (ServiceException e) {
            LOGGER.log(Level.ERROR, e.getMessage());
            throw new RuntimeException("Can't work with empty or null file.");
        }
    }
}
