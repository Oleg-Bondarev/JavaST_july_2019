package by.training.composite.service;

import by.training.composite.service.interfaces.ConsoleService;
import by.training.composite.service.interfaces.FileService;
import by.training.composite.service.properties.PropertiesReader;

/**
 * Service factory.
 * */
public final class ServiceFactory {
    /**
     * Instance.
     * */
    private static final ServiceFactory INSTANCE = new ServiceFactory();
    /**
     * File service.
     * */
    private final FileService fileService = new FileServiceImpl();
    /**
     * Console service.
     * */
    private final ConsoleService consoleService = new ConsoleServiceImpl();
    /**
     * Creating text tree.
     * */
    private final TextTree textTree = TextTree.getInstance();
   /**
     * Properties reader.
     * */
    private final PropertiesReader propertiesReader
            = PropertiesReader.getPropertiesReader();
    /**
     * @return class instance.
     * */
    public static ServiceFactory getInstance() {
        return INSTANCE;
    }
    /**
     * @return file service.
     * */
    public FileService getFileService() {
        return fileService;
    }
    /**
     * @return console service.
     * */
    public ConsoleService getConsoleService() {
        return consoleService;
    }
    /**
     * @return text tree.
     * */
    public TextTree getTextTree() {
        return textTree;
    }
    /**
     * @return properties reader.
     * */
    public PropertiesReader getPropertiesReader() {
        return propertiesReader;
    }
}
