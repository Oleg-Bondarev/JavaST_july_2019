package by.training.train.controller.command;

import java.util.List;

import by.training.train.controller.interfaces.Command;
import by.training.train.service.exception.ServiceException;
import by.training.train.service.factory.ServiceFactory;
import by.training.train.service.interfaces.CarriageService;
import by.training.train.service.interfaces.FileService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ReadCarriages implements Command {
    /**
     * Regex.
     * */
    private static final String REGEX_FOR_SPLIT = ";";
    /***/
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    /***/
    static final Logger LOGGER = LogManager.getLogger(ReadCarriages.class);
    /**
     * @param request - string with type of request in the first part and
     *                path to file in the second part.
     * */
    @Override
    public String executeCommand(final String request) {
        String[] parameters = request.trim().split(REGEX_FOR_SPLIT);
        String pathToFile = parameters[1];
        StringBuilder report = new StringBuilder();
        FileService fileService = serviceFactory.getFileService();
        CarriageService carriageService = serviceFactory.getCarriageService();
        List<String> carriagesList;
        int successAdd = 0;
        int failAdd = 0;

        try {
            carriagesList = fileService.read(pathToFile);
        } catch (ServiceException eNew) {
            LOGGER.log(Level.ERROR, eNew.getMessage());
            report.append(eNew.getMessage());
            return report.toString();
        }
        for (String params : carriagesList) {
            try {
                carriageService.addCarriage(params);
                successAdd++;
            } catch (ServiceException eNew) {
                LOGGER.log(Level.ERROR, eNew.getMessage());
                failAdd++;
            }
        }
        report.append("Count of success added carriages: ");
        report.append(successAdd + "\n");
        report.append("Count of failed added carriages: ");
        report.append(failAdd);
        return report.toString();
    }
}
