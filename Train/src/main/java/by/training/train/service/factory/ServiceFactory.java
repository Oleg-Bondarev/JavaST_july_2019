package by.training.train.service.factory;

import by.training.train.service.impl.CarriageServiceImpl;
import by.training.train.service.impl.FileServiceImpl;
import by.training.train.service.impl.TrainServiceImpl;
import by.training.train.service.interfaces.CarriageService;
import by.training.train.service.interfaces.FileService;
import by.training.train.service.interfaces.TrainService;

public final
class ServiceFactory {
    /**
     * Class instance.
     * */
    private static final ServiceFactory INSTANCE = new ServiceFactory();
    /***/
    private final CarriageService carriageService = new CarriageServiceImpl();
    /***/
    private final FileService fileService = new FileServiceImpl();
    /***/
    private final TrainService trainService = new TrainServiceImpl();
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
    public CarriageService getCarriageService() {
        return carriageService;
    }
    /**
     * @return object of file service.
     * */
    public FileService getFileService() {
        return fileService;
    }
    /**
     * @return object of train service.
     * */
    public TrainService getTrainService() {
        return trainService;
    }
}
