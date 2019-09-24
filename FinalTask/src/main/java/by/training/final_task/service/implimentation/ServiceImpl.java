package by.training.final_task.service.implimentation;

import by.training.final_task.dao.interfases.DAO;
import by.training.final_task.dao.sql.*;
import by.training.final_task.service.ServiceException;
import by.training.final_task.service.interfaces.Service;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ServiceImpl implements Service {

    private static final Logger LOGGER = LogManager.getLogger();
    protected Connection connection;

    public ServiceImpl(final Connection newConnection) {
        connection = newConnection;
    }

    protected BaseDaoImpl createDAO(DAOEnum newClass) {
        switch (newClass) {
            case USER: return new UserDaoImpl(connection);
            case COUPON: return new CouponDaoImpl(connection);
            case REVIEWS: return new ReviewsDaoImpl(connection);
            case CATEGORY: return new CategoryDaoImpl(connection);
            case COUPONUSER: return new CouponUserDaoImpl(connection);
            case COMPANYPROVIDER: return new CompanyProviderDaoImpl(connection);
            default: return null;
        }
    }

    protected void commitTransaction() throws ServiceException {
        try {
            connection.commit();
        } catch (SQLException newE) {
            LOGGER.log(Level.ERROR, "It is impossible to commit" +
                    " transaction.", newE);
            throw new ServiceException(newE.getMessage(), newE);
        }
    }

    protected void rollbackTransaction() throws ServiceException {
        try {
            connection.rollback();
        } catch (SQLException newE) {
            LOGGER.log(Level.ERROR, "It is impossible to rollback" +
                    " transaction.", newE);
            throw new ServiceException(newE.getMessage(), newE);
        }
    }
}
