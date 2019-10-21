package by.training.final_task.service.implimentation;

import by.training.final_task.dao.interfases.AbstractConnectionManager;
import by.training.final_task.dao.interfases.CompanyProviderDAO;
import by.training.final_task.dao.interfases.DaoFactory;
import by.training.final_task.dao.sql.ConnectionManager;
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

public class CompanyProviderImpl extends AbstractService
                                implements CompanyProviderService {

    public CompanyProviderImpl() {
        super();
    }

    public CompanyProviderImpl(final DaoFactory newFactory) {
        super(newFactory);
    }

    @Override
    public CompanyProvider getByPhone(final int phone) throws ServiceException {
        try (AbstractConnectionManager connectionManager = new ConnectionManager()) {
            try {
                CompanyProviderDAO companyProviderDAO = getDaoFactory().createCompanyProviderDAO(connectionManager);
                return companyProviderDAO.getByPhone(phone);
            } catch (PersistentException newE) {
                connectionManager.rollbackChange();
                throw new ServiceException(newE.getMessage(), newE);
            }
        } catch (PersistentException newE) {
            throw new ServiceException(newE);
        }
    }

    @Override
    public List<CompanyProvider> getAllAvailableCompany(final int offset,
                                                        final int limit)
            throws ServiceException {
        try (AbstractConnectionManager connectionManager = new ConnectionManager()) {
            try {
                CompanyProviderDAO companyProviderDAO = getDaoFactory().createCompanyProviderDAO(connectionManager);
                return companyProviderDAO.getAllAvailableCompany(offset, limit);
            } catch (PersistentException newE) {
                connectionManager.rollbackChange();
                throw new ServiceException(newE.getMessage(), newE);
            }
        } catch (PersistentException newE) {
            throw new ServiceException(newE);
        }
    }

    @Override
    public List<CompanyProvider> getByCompanyName(final String name,
                                              final int offset, final int limit)
            throws ServiceException {
        try (AbstractConnectionManager connectionManager = new ConnectionManager()) {
            try {
                CompanyProviderDAO companyProviderDAO = getDaoFactory()
                        .createCompanyProviderDAO(connectionManager);
                return companyProviderDAO.getByCompanyName(name, offset - 1, limit);
            } catch (PersistentException newE) {
                connectionManager.rollbackChange();
                throw new ServiceException(newE.getMessage(), newE);
            }
        } catch (PersistentException newE) {
            throw new ServiceException(newE);
        }
    }

    @Override
    public int getAmountOfCompany() throws ServiceException {
        try (AbstractConnectionManager connectionManager = new ConnectionManager()) {
            try {
                CompanyProviderDAO companyProviderDAO = getDaoFactory().createCompanyProviderDAO(connectionManager);
                return companyProviderDAO.getAmountOfCompany();
            } catch (PersistentException newE) {
                connectionManager.rollbackChange();
                throw new ServiceException(newE.getMessage(), newE);
            }
        } catch (PersistentException newE) {
            throw new ServiceException(newE);
        }
    }

    @Override
    public int getAmountOfAvailableCompany() throws ServiceException {
        try (AbstractConnectionManager connectionManager = new ConnectionManager()) {
            try {
                CompanyProviderDAO companyProviderDAO = getDaoFactory().createCompanyProviderDAO(connectionManager);
                return companyProviderDAO.getAmountOfAvailableCompany();
            } catch (PersistentException newE) {
                connectionManager.rollbackChange();
                throw new ServiceException(newE.getMessage(), newE);
            }
        } catch (PersistentException newE) {
            throw new ServiceException(newE);
        }
    }

    @Override
    public int getAmountByCompanyName(final String name) throws ServiceException {
        try (AbstractConnectionManager connectionManager =
                     new ConnectionManager()) {
            try {
                CompanyProviderDAO companyProviderDAO = getDaoFactory()
                        .createCompanyProviderDAO(connectionManager);
                return companyProviderDAO.getAmountByName(name);
            } catch (PersistentException newE) {
                connectionManager.rollbackChange();
                throw new ServiceException(newE.getMessage(), newE);
            }
        } catch (PersistentException newE) {
            throw new ServiceException(newE);
        }
    }

    @Override
    public int create(final CompanyProvider company) throws ServiceException {
        try (AbstractConnectionManager connectionManager = new ConnectionManager()) {
            try {
                CompanyProviderDAO companyProviderDAO = getDaoFactory().createCompanyProviderDAO(connectionManager);
                int companyProviderId = companyProviderDAO.create(company);
                company.setId(companyProviderId);
                connectionManager.commitChange();
                return companyProviderId;
            } catch (PersistentException newE) {
                connectionManager.rollbackChange();
                throw new ServiceException(newE.getMessage(), newE);
            }
        } catch (PersistentException newE) {
            throw new ServiceException(newE);
        }
    }

    @Override
    public boolean update(final CompanyProvider company)
            throws ServiceException {
        try (AbstractConnectionManager connectionManager = new ConnectionManager()) {
            try {
                CompanyProviderDAO companyProviderDAO = getDaoFactory().createCompanyProviderDAO(connectionManager);
                boolean statement = companyProviderDAO.update(company);
                connectionManager.commitChange();
                return statement;
            } catch (PersistentException newE) {
                connectionManager.rollbackChange();
                throw new ServiceException(newE.getMessage(), newE);
            }
        } catch (PersistentException newE) {
            throw new ServiceException(newE);
        }
    }

    @Override
    public boolean updateCompanyStatus(final long id) throws ServiceException {
        try (AbstractConnectionManager connectionManager = new ConnectionManager()) {
            try {
                CompanyProviderDAO companyProviderDAO = getDaoFactory().createCompanyProviderDAO(connectionManager);
                boolean statement = companyProviderDAO.updateCompanyStatus(id);
                connectionManager.commitChange();
                return statement;
            } catch (PersistentException newE) {
                connectionManager.rollbackChange();
                throw new ServiceException(newE.getMessage(), newE);
            }
        } catch (PersistentException newE) {
            throw new ServiceException(newE);
        }
    }
}
