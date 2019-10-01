package by.training.final_task.service.implimentation;

import by.training.final_task.dao.interfases.AbstractConnectionManager;
import by.training.final_task.dao.interfases.CategoryDAO;
import by.training.final_task.dao.interfases.DaoFactory;
import by.training.final_task.dao.sql.ConnectionManager;
import by.training.final_task.dao.sql.DAOEnum;
import by.training.final_task.entity.Category;
import by.training.final_task.dao.PersistentException;
import by.training.final_task.service.ServiceException;
import by.training.final_task.service.interfaces.CategoryService;

import java.util.List;

public class CategoryServiceImpl extends AbstractService
                                 implements CategoryService {
    
    public CategoryServiceImpl() {
        super();
    }

    public CategoryServiceImpl(final DaoFactory newFactory) {
        super(newFactory);
    }

    //+
    @Override
    public Category get(final long id) throws ServiceException {
        try (AbstractConnectionManager connectionManager = new ConnectionManager()) {
            try {
                CategoryDAO categoryDAO = getDaoFactory().createCategoryDAO(connectionManager);
                return categoryDAO.get(id);
            } catch (PersistentException newE) {
                connectionManager.rollbackChange();
                throw new ServiceException(newE.getMessage(), newE);
            }
        } catch (PersistentException newE) {
            throw new ServiceException(newE);
        }
    }

    @Override
    public List<Category> getAll() throws ServiceException {
        try (AbstractConnectionManager connectionManager = new ConnectionManager()) {
            try {
                CategoryDAO categoryDAO = getDaoFactory().createCategoryDAO(connectionManager);
                return categoryDAO.getAll();
            } catch (PersistentException newE) {
                connectionManager.rollbackChange();
                throw new ServiceException(newE.getMessage(), newE);
            }
        } catch (PersistentException newE) {
            throw new ServiceException(newE);
        }
    }

    @Override
    public int getAmountOfCategory() throws ServiceException {
        try (AbstractConnectionManager connectionManager = new ConnectionManager()) {
            try {
                CategoryDAO categoryDAO = getDaoFactory().createCategoryDAO(connectionManager);
                return categoryDAO.getAmountOfCategory();
            } catch (PersistentException newE) {
                connectionManager.rollbackChange();
                throw new ServiceException(newE.getMessage(), newE);
            }
        } catch (PersistentException newE) {
            throw new ServiceException(newE);
        }
    }

    @Override
    public int create(final Category category) throws ServiceException {
        try (AbstractConnectionManager connectionManager = new ConnectionManager()) {
            try {
                CategoryDAO categoryDAO = getDaoFactory().createCategoryDAO(connectionManager);
                int categoryId = categoryDAO.create(category);
                category.setId(categoryId);
                connectionManager.commitChange();
                return categoryId;
            } catch (PersistentException newE) {
                connectionManager.rollbackChange();
                throw new ServiceException(newE.getMessage(), newE);
            }
        } catch (PersistentException newE) {
            throw new ServiceException(newE);
        }
    }

    @Override
    public boolean update(final Category category) throws ServiceException {
        try (AbstractConnectionManager connectionManager = new ConnectionManager()) {
            try {
                CategoryDAO categoryDAO = getDaoFactory().createCategoryDAO(connectionManager);
                boolean statement = categoryDAO.update(category);
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
