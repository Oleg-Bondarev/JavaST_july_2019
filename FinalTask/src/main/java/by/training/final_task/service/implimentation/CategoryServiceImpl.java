package by.training.final_task.service.implimentation;

import by.training.final_task.dao.interfases.CategoryDAO;
import by.training.final_task.dao.sql.DAOEnum;
import by.training.final_task.entity.Category;
import by.training.final_task.exception.PersistentException;
import by.training.final_task.service.ServiceException;
import by.training.final_task.service.interfaces.CategoryService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class CategoryServiceImpl extends ServiceImpl
                                 implements CategoryService {
    
    private static final Logger LOGGER = LogManager.getLogger();
    private CategoryDAO categoryDAO;
    
    public CategoryServiceImpl(final Connection newConnection) {
        super(newConnection);
    }

    @Override
    public Category get(final long id) throws ServiceException {
        try {
            prepareCategoryDao(DAOEnum.CATEGORY);
            Category category = categoryDAO.get(id);
            commitAndChangeAutoCommit();
            return category;
        } catch (PersistentException newE) {
            throw new ServiceException(newE.getMessage(), newE);
        }
    }

    @Override
    public List<Category> getAll() throws ServiceException {
        try {
            prepareCategoryDao(DAOEnum.CATEGORY);
            List<Category> categoryList = categoryDAO.getAll();
            commitAndChangeAutoCommit();
            return categoryList;
        } catch (PersistentException newE) {
            throw new ServiceException(newE.getMessage(), newE);
        }
    }

    @Override
    public int getAmountOfCategory() throws ServiceException {
        try {
            prepareCategoryDao(DAOEnum.CATEGORY);
            int amount = categoryDAO.getAmountOfCategory();
            commitAndChangeAutoCommit();
            return amount;
        } catch (PersistentException newE) {
            throw new ServiceException(newE.getMessage(), newE);
        }
    }

    private void prepareCategoryDao(final DAOEnum classType)
            throws ServiceException {
        try {
            connection.setAutoCommit(false);
            categoryDAO = (CategoryDAO) createDAO(classType);
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
