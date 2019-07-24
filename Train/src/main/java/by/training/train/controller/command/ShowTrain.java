package by.training.train.controller.command;

import java.util.ArrayList;
import java.util.List;

import by.training.train.controller.interfaces.Command;
import by.training.train.entity.carriage.PassengerCarriage;
import by.training.train.service.exception.ServiceException;
import by.training.train.service.factory.ServiceFactory;
import by.training.train.service.interfaces.FileService;
import by.training.train.service.interfaces.TrainService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ShowTrain implements Command {
    /**
     * Regex.
     * */
    private static final String REGEX_FOR_SPLIT = ";";
    /***/
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    /***/
    static final Logger LOGGER = LogManager.getLogger(ShowTrain.class);
    /***/
    @Override
    public String executeCommand(final String request) {
        String[] parameters = request.trim().split(REGEX_FOR_SPLIT);
        StringBuilder report = new StringBuilder();
        String path = parameters[1];
        FileService fileService = serviceFactory.getFileService();
        TrainService trainService = serviceFactory.getTrainService();
        List<PassengerCarriage> passengerCarriageList = trainService.getTrain();
        int len = passengerCarriageList.size();
        List<String> stringList = new ArrayList<>(len);
        for (int i = 0; i < len; i++) {
            stringList.add(passengerCarriageList.get(i).toString());
        }
        try {
            fileService.write(path, stringList);
            report.append("Writing in file - OK.");
        } catch (ServiceException eNew) {
            LOGGER.log(Level.ERROR, eNew.getMessage());
            report.append("Some problems with writing in file.");
        }
        return report.toString();
    }
}
