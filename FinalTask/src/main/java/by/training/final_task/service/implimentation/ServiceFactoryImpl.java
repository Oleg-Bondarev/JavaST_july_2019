package by.training.final_task.service.implimentation;

import by.training.final_task.dao.PersistentException;
import by.training.final_task.dao.sql.ConnectionManager;
import by.training.final_task.dao.sql.DAOEnum;
import by.training.final_task.service.ServiceException;
import by.training.final_task.service.interfaces.Service;
import by.training.final_task.service.interfaces.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ServiceFactoryImpl implements ServiceFactory {

    AbstractService abstractService = new AbstractService();
    ConnectionManager connectionManager;

    public ServiceFactoryImpl() throws ServiceException {
        try {
            connectionManager = new ConnectionManager();
        } catch (PersistentException newE) {
            throw new ServiceException(newE.getMessage(), newE);
        }
    }

    @Override
    public Service createService(DAOEnum key) throws ServiceException {
        if (key != null) {
            return createServiceInstance(key);
        }
        return null;
    }

    @Override
    public void close() throws ServiceException {
        try {
            connectionManager.close();
        } catch (PersistentException newE) {
            throw new ServiceException(newE.getMessage(), newE);
        }
    }

    private AbstractService createServiceInstance(final DAOEnum key) {
        switch (key) {
            case CATEGORY:
                return new CategoryServiceImpl(abstractService.getDaoFactory());
            case COMPANYPROVIDER:
                return new CompanyProviderImpl(abstractService.getDaoFactory());
            case COUPON:
                return new CouponServiceImpl(abstractService.getDaoFactory());
            case COUPONUSER:
                return new CouponUserImpl(abstractService.getDaoFactory());
            case REVIEWS:
                return new ReviewServiceImpl(abstractService.getDaoFactory());
            case USER:
                return new UserServiceImpl(abstractService.getDaoFactory());
            default:
                throw new IllegalArgumentException("Cannot create service"
                        + " instance! - Incorrect ENUM type.");
        }
    }
}
