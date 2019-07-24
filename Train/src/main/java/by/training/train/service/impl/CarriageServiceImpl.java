package by.training.train.service.impl;

import java.util.List;
import java.util.Optional;

import by.training.train.dao.CarriageDAO;
import by.training.train.dao.PassengerCarriageProvider;
import by.training.train.dao.exception.DAOException;
import by.training.train.dao.factory.CarriageFactoryInterface;
import by.training.train.dao.factory.DAOFactory;
import by.training.train.entity.carriage.PassengerCarriage;
import by.training.train.service.exception.ServiceException;
import by.training.train.service.specification.interfaces.Specification;
import by.training.train.service.interfaces.CarriageService;
import by.training.train.validator.Validator;

public class CarriageServiceImpl implements CarriageService {
    /**
     * Regex for split of the text file.
     */
    private static final String REGEX_FOR_SPLIT = ",";
    /***/
    private final PassengerCarriageProvider provider =
            new PassengerCarriageProvider();
    /***/
    private DAOFactory daoFactory = DAOFactory.getInstance();
    /***/
    @Override
    public void addCarriage(final String initArguments)
            throws ServiceException {
        Validator validator = new Validator();
        PassengerCarriage passCarriage;
        CarriageFactoryInterface factory;
        if ((initArguments == null) || initArguments.isEmpty()) {
            throw new ServiceException("Incorrect parameters to create object."
                    + " It must be not null and not empty sting.");
        }
        String[] parameters = initArguments.trim().split(REGEX_FOR_SPLIT);
        String carriageType = parameters[0];

        if (!validator.validateInformation(parameters)) {
            throw new ServiceException("Incorrect parameters. "
                    + "See details in log.");
        }

        try {
            factory = provider.getCarriageFactory(carriageType);
            passCarriage = factory.createTrainCarriage(parameters);
        } catch (DAOException eNew) {
            throw new ServiceException(eNew);
        }

        CarriageDAO carriageDAO = daoFactory.getCarriageDAO();
        carriageDAO.addCarriage(Optional.of(passCarriage));
    }
    /***/
    @Override
    public void removeCarriage(final PassengerCarriage trainCarriageNew) {
        CarriageDAO carriageDAO = daoFactory.getCarriageDAO();
        carriageDAO.removeCarriage(Optional.of(trainCarriageNew));
    }
    /***/
    @Override
    public List<PassengerCarriage> query(final Specification specificationNew) {
        CarriageDAO carriageDAO = daoFactory.getCarriageDAO();
        return carriageDAO.query(specificationNew);
    }
}
