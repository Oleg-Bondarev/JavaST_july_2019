package by.training.final_task.dao.sql;

import by.training.final_task.dao.interfases.CategoryDAO;
import by.training.final_task.entity.Category;
import by.training.final_task.entity.WrongEnumTupeException;
import by.training.final_task.dao.PersistentException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class CategoryDaoImpl extends BaseDaoImpl implements CategoryDAO {

    private static final Logger LOGGER = LogManager.getLogger();

    private static final String GET_CATEGORY = "SELECT category.id, category.name " +
            "FROM category WHERE id = ?";
    private static final String GET_ALL_USERS = "SELECT category.id, category.name " +
            "FROM category ORDER BY id";
    private static final String GET_AMOUNT_OF_ALL_CATEGORY =
            "SELECT COUNT(category.id) FROM category";

    public CategoryDaoImpl(final Connection newConnection) {
        super(newConnection);
    }
    //+
    @Override
    public Category get() throws PersistentException {
        return get(1);
    }
    //+
    @Override
    public Category get(final long id) throws PersistentException {
        Category category = null;
        try (PreparedStatement preparedStatement = getConnection()
                .prepareStatement(GET_CATEGORY)) {
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    category = takeCategory(resultSet);
                }
            } catch (WrongEnumTupeException newE) {
                LOGGER.log(Level.WARN, newE.getMessage(), newE);
                throw new PersistentException(newE.getMessage(), newE);
            }
        } catch (SQLException newE) {
            LOGGER.log(Level.WARN, newE.getMessage(), newE);
            throw new PersistentException(newE.getMessage(), newE);
        }
        return category;
    }
    //+
    @Override
    public List<Category> getAll() throws PersistentException {
        List<Category> users = new LinkedList<>();
        try (PreparedStatement preparedStatement = getConnection()
                .prepareStatement(GET_ALL_USERS)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    users.add(takeCategory(resultSet));
                }
            } catch (WrongEnumTupeException newE) {
                LOGGER.log(Level.WARN, newE.getMessage(), newE);
                throw new PersistentException(newE.getMessage(), newE);
            }
            return users;
        } catch (SQLException newE) {
            LOGGER.log(Level.WARN, newE.getMessage(), newE);
            throw new PersistentException(newE.getMessage(), newE);
        }    }
    //+
    @Override
    public int getAmountOfCategory() throws PersistentException {
        try (PreparedStatement preparedStatement = getConnection()
                .prepareStatement(GET_AMOUNT_OF_ALL_CATEGORY)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                resultSet.next();
                return resultSet.getInt(1);
            }
        } catch (SQLException newE) {
            LOGGER.log(Level.WARN, newE.getMessage(), newE);
            throw new PersistentException(newE.getMessage(), newE);
        }
    }

    private Category takeCategory(ResultSet newResultSet) throws SQLException,
            WrongEnumTupeException {
        String name = newResultSet.getNString("name");
        return Category.fromValue(name);
    }
}
