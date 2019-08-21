package by.training.composite.service;

import by.training.composite.dao.TextRepository;
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
     * Creating text tree.
     * */
    private final TextTree textTree = new TextTree();
    /**
     * Text repository.
     * */
    private final TextRepository textRepository = TextRepository.getInstance();
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
     * @return text tree.
     * */
    public TextTree getTextTree() {
        return textTree;
    }
    /**
     * @return text repository
     * */
    public TextRepository getTextRepository() {
        return textRepository;
    }
    /**
     * @return properties reader.
     * */
    public PropertiesReader getPropertiesReader() {
        return propertiesReader;
    }
}