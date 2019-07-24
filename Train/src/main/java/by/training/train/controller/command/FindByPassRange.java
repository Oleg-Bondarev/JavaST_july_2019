package by.training.train.controller.command;

import java.util.ArrayList;
import java.util.List;

import by.training.train.controller.interfaces.Command;
import by.training.train.entity.carriage.PassengerCarriage;
import by.training.train.service.exception.ServiceException;
import by.training.train.service.specification.find.FindByPassengerRange;
import by.training.train.service.factory.ServiceFactory;
import by.training.train.service.interfaces.CarriageService;
import by.training.train.service.interfaces.FileService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FindByPassRange implements Command {
    /**
     * Regex.
     * */
    private static final String REGEX_FOR_SPLIT = ";";
    /***/
    private static final int THREE = 3;
    /***/
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    /***/
    static final Logger LOGGER = LogManager.
            getLogger(FindByPassengerRange.class);
    /**
     * @param request - string with type of request in the first part, lower
     *             border in the second part and upper border in the third part.
     * */
    @Override
    public String executeCommand(final String request) {
        String[] parameters = request.trim().split(REGEX_FOR_SPLIT);
        int lowerBorder = Integer.parseInt(parameters[1]);
        int upperBorder = Integer.parseInt(parameters[2]);
        String path = parameters[THREE];
        StringBuilder report = new StringBuilder();
        FileService fileService = serviceFactory.getFileService();
        CarriageService carriageService = serviceFactory.getCarriageService();
        List<PassengerCarriage> carriagesList = carriageService.
                query(new FindByPassengerRange(lowerBorder, upperBorder));

        if (!carriagesList.isEmpty()) {
            report.append("Was found: " + carriagesList.size()
                                                            + " carriages.\n");
            List<String> findCarriages = new ArrayList<>(carriagesList.size());
            for (PassengerCarriage carriage : carriagesList) {
                findCarriages.add(carriage.toString());
            }
            try {
                fileService.write(path, findCarriages);
            } catch (ServiceException eNew) {
                LOGGER.log(Level.ERROR, eNew.getMessage());
            }
            return report.toString();
        } else {
            report.append("Wasn't found some carriages in the train.");
            return report.toString();
        }
    }
}

