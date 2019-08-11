package by.training.multithreading_matrix.service;

import by.training.multithreading_matrix.service.interfaces.FileService;
import by.training.multithreading_matrix.service.interfaces.MatrixService;

/**
 * Represent service factory.
 * */
public final class ServiceFactory {
    /**
     * Instance.
     * */
    private static final ServiceFactory INSTANCE = new ServiceFactory();
    /**
     * Matrix service.
     * */
    private final MatrixService matrixService = new MatrixServiceImpl();
    /**
     * File service.
     * */
    private final FileService fileService = new FileServiceImpl();
    /**
     * Default constructor.
     * */
    private ServiceFactory() { }
    /**
     * @return class instance.
     * */
    public static ServiceFactory getInstance() {
        return INSTANCE;
    }
    /**
     * @return object of carriage service.
     * */
    public MatrixService getMatrixService() {
        return matrixService;
    }
    /**
     * @return object of file service.
     * */
    public FileService getFileService() {
        return fileService;
    }
}
