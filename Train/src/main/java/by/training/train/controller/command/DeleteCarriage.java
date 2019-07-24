package by.training.train.controller.command;

import java.util.List;

import by.training.train.controller.interfaces.Command;
import by.training.train.entity.carriage.PassengerCarriage;
import by.training.train.service.factory.ServiceFactory;
import by.training.train.service.interfaces.CarriageService;
import by.training.train.service.specification.find.FindByIdSpecification;
import by.training.train.validator.Validator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DeleteCarriage implements Command {
    /**
     * Regex.
     * */
    private static final String REGEX_FOR_SPLIT = ";";
    /***/
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    /***/
    static final Logger LOGGER = LogManager.getLogger(DeleteCarriage.class);
    /**
     * @param request - string with type of request in the first part and
 *                ID of the carriage that we want to delete in the second part.
     * */
    @Override
    public String executeCommand(final String request) {
        String[] parameters = request.trim().split(REGEX_FOR_SPLIT);
        StringBuilder report = new StringBuilder();
        int identificator;
        Validator validator = new Validator();

        if (!validator.validateInt(parameters[1])) {
            LOGGER.log(Level.ERROR, "Incorrect parameter: ID=" + parameters[1]);
            report.append("Deletion failed. Incorrect parameter entered: ID="
                    + parameters[1]);
            return report.toString();
        }
        identificator = Integer.parseInt(parameters[1]);
        CarriageService carriageService = serviceFactory.getCarriageService();
        List<PassengerCarriage> deleteCerriage = carriageService.
                                query(new FindByIdSpecification(identificator));
        if (deleteCerriage.size() != 0) {
            carriageService.removeCarriage(deleteCerriage.get(0));
            report.append("Carriage removal was successful.");
            return report.toString();
        } else {
            report.append("Nothing to remove. There is no carriage with such"
                    + " an identifier in the train.");
            return report.toString();
        }
    }
}
