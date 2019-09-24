package by.training.final_task.service.implimentation;

import by.training.final_task.dao.interfases.CompanyProviderDAO;
import by.training.final_task.dao.sql.DAOEnum;
import by.training.final_task.entity.CompanyProvider;
import by.training.final_task.dao.PersistentException;
import by.training.final_task.service.ServiceException;
import by.training.final_task.service.interfaces.CompanyProviderService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class CompanyProviderImpl extends ServiceImpl
                                implements CompanyProviderService {

    private static final Logger LOGGER = LogManager.getLogger();
    private CompanyProviderDAO companyProviderDAO;

    public CompanyProviderImpl(Connection newConnection) {
        super(newConnection);
    }

    @Override
    public CompanyProvider getByAddress(final String address)
            throws ServiceException {
        try {
            prepareCompanyDao(DAOEnum.COMPANYPROVIDER);
            CompanyProvider company = companyProviderDAO.getByAddress(address);
            commitAndChangeAutoCommit();
            return company;
        } catch (PersistentException newE) {
            rollbackTransaction();
            throw new ServiceException(newE.getMessage(), newE);
        }
    }

    @Override
    public CompanyProvider getByPhone(final int phone) throws ServiceException {
        try {
            prepareCompanyDao(DAOEnum.COMPANYPROVIDER);
            CompanyProvider company = companyProviderDAO.getByPhone(phone);
            commitAndChangeAutoCommit();
            return company;
        } catch (PersistentException newE) {
            rollbackTransaction();
            throw new ServiceException(newE.getMessage(), newE);
        }
    }

    @Override
    public List<CompanyProvider> getAllAvailableCompany(final int offset,
                                                        final int limit)
            throws ServiceException {
        try {
            prepareCompanyDao(DAOEnum.COUPON);
            List<CompanyProvider> couponList = companyProviderDAO
                    .getAllAvailableCompany(offset, limit);
            commitAndChangeAutoCommit();
            return couponList;
        } catch (PersistentException newE) {
            rollbackTransaction();
            throw new ServiceException(newE.getMessage(), newE);
        }
    }

    @Override
    public int getAmountOfCompany() throws ServiceException {
        try {
            prepareCompanyDao(DAOEnum.COMPANYPROVIDER);
            int amount = companyProviderDAO.getAmountOfCompany();
            commitAndChangeAutoCommit();
            return amount;
        } catch (PersistentException newE) {
            rollbackTransaction();
            throw new ServiceException(newE.getMessage(), newE);
        }
    }

    @Override
    public int getAmountOfAvailableCompany() throws ServiceException {
        try {
            prepareCompanyDao(DAOEnum.COMPANYPROVIDER);
            int amount = companyProviderDAO.getAmountOfAvailableCompany();
            commitAndChangeAutoCommit();
            return amount;
        } catch (PersistentException newE) {
            rollbackTransaction();
            throw new ServiceException(newE.getMessage(), newE);
        }
    }

    @Override
    public int create(final CompanyProvider company) throws ServiceException {
        try {
            prepareCompanyDao(DAOEnum.COMPANYPROVIDER);
            int index = companyProviderDAO.create(company);
            commitAndChangeAutoCommit();
            return index;
        } catch (PersistentException newE) {
            rollbackTransaction();
            throw new ServiceException(newE.getMessage(), newE);
        }
    }

    @Override
    public boolean update(final CompanyProvider company)
            throws ServiceException {
        try {
            prepareCompanyDao(DAOEnum.COMPANYPROVIDER);
            boolean statement = companyProviderDAO.update(company);
            commitAndChangeAutoCommit();
            return statement;
        } catch (PersistentException newE) {
            rollbackTransaction();
            throw new ServiceException(newE.getMessage(), newE);
        }
    }

    @Override
    public boolean updateCompanyStatus(final long id) throws ServiceException {
        try {
            prepareCompanyDao(DAOEnum.COMPANYPROVIDER);
            boolean statement = companyProviderDAO.updateCompanyStatus(id);
            commitAndChangeAutoCommit();
            return statement;
        } catch (PersistentException newE) {
            rollbackTransaction();
            throw new ServiceException(newE.getMessage(), newE);
        }
    }

    private void prepareCompanyDao(final DAOEnum classType)
            throws ServiceException {
        try {
            connection.setAutoCommit(false);
            companyProviderDAO = (CompanyProviderDAO) createDAO(classType);
        } catch (SQLException newE) {
            rollbackTransaction();
            LOGGER.log(Level.ERROR, "Have some problems in setting" +
                    " autocommit transaction property.", newE);
            throw new ServiceException(newE.getMessage(), newE);
        }
    }

    private void commitAndChangeAutoCommit() throws ServiceException {
        try {
            connection.setAutoCommit(true);
        } catch (SQLException newE) {
            rollbackTransaction();
            LOGGER.log(Level.ERROR, "Have some problems in setting" +
                    " autocommit transaction property.", newE);
            throw new ServiceException(newE.getMessage(), newE);
        }
    }
}
