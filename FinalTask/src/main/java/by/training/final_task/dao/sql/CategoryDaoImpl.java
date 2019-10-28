package by.training.final_task.dao.sql;

import by.training.final_task.dao.interfases.AbstractConnectionManager;
import by.training.final_task.dao.interfases.CategoryDAO;
import by.training.final_task.entity.Category;
import by.training.final_task.dao.PersistentException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class CategoryDaoImpl extends AbstractDao<Category>
        implements CategoryDAO {

    private static final Logger LOGGER = LogManager.getLogger();

    private static final String ADD_CATEGORY =
            "INSERT INTO category (name) VALUES (?)";
    private static final String UPDATE_CATEGORY =
            "UPDATE category SET category.name=? WHERE category.id=?";
    private static final String GET_ALL_CATEGORIES =
            "SELECT category.id, category.name FROM category LIMIT ? OFFSET ?";
    private static final String GET_CATEGORY =
            "SELECT category.id, category.name FROM category WHERE id = ?";
    private static final String GET_ALL_USERS =
            "SELECT category.id, category.name FROM category ORDER BY id";
    private static final String GET_AMOUNT_OF_ALL_CATEGORY =
            "SELECT COUNT(category.id) FROM category";

    public CategoryDaoImpl(final AbstractConnectionManager newConnection) {
        super(newConnection);
    }

    @Override
    public int create(final Category newCategory) throws PersistentException {
        LOGGER.log(Level.WARN,
                "Invalid operation create for category type.");
        throw new PersistentException(
                "Invalid operation create for category type.");
    }

    @Override
    public Category get() throws PersistentException {
        return get(1);
    }

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
            }
        } catch (SQLException newE) {
            LOGGER.log(Level.WARN, newE.getMessage(), newE);
            throw new PersistentException(newE.getMessage(), newE);
        }
        return category;
    }

    @Override
    public List<Category> getAll(final int offset, final int limit)
            throws PersistentException {
        List<Category> categories = new LinkedList<>();
        try (PreparedStatement preparedStatement = getConnection()
                .prepareStatement(GET_ALL_CATEGORIES)) {
            preparedStatement.setInt(1, limit);
            preparedStatement.setInt(2, offset);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    categories.add(takeCategory(resultSet));
                }
            }
            return categories;
        } catch (SQLException newE) {
            LOGGER.log(Level.WARN, newE.getMessage(), newE);
            throw new PersistentException(newE.getMessage(), newE);
        }
    }

    @Override
    public boolean update(final Category newCategory)
            throws PersistentException {
        LOGGER.log(Level.WARN, "Invalid operation update for category type.");
        throw new PersistentException("Invalid operation update for category type.");
    }

    @Override
    public boolean delete(final Category element) throws PersistentException {
        LOGGER.log(Level.WARN,
                "Invalid operation to delete coupon category.");
        throw new PersistentException(
                "Invalid operation to delete coupon category.");
    }

    @Override
    public boolean delete(final long id) throws PersistentException {
        LOGGER.log(Level.WARN,
                "Invalid operation to delete coupon category.");
        throw new PersistentException(
                "Invalid operation to delete coupon category.");
    }

    @Override
    public List<Category> getAll() throws PersistentException {
        List<Category> users = new LinkedList<>();
        try (PreparedStatement preparedStatement = getConnection()
                .prepareStatement(GET_ALL_USERS)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    users.add(takeCategory(resultSet));
                }
            }
            return users;
        } catch (SQLException newE) {
            LOGGER.log(Level.WARN, newE.getMessage(), newE);
            throw new PersistentException(newE.getMessage(), newE);
        }
    }

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

    private Category takeCategory(final ResultSet newResultSet)
            throws SQLException {
        long id = newResultSet.getLong("id");
        String name = newResultSet.getNString("name");
        return new Category(id, name);
    }
}
