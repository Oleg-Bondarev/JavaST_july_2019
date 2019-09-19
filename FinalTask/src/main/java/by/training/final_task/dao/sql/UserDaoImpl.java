package by.training.final_task.dao.sql;

import by.training.final_task.dao.interfases.UserDAO;
import by.training.final_task.entity.Role;
import by.training.final_task.entity.User;
import by.training.final_task.exception.PersistentException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class UserDaoImpl extends BaseDaoImpl implements UserDAO {

    private static final Logger LOGGER = LogManager.getLogger();

    private static final String ADD_USER = "INSERT INTO user (login, password,"
            + " role, email, avatar, first_name, second_name, mobile_phone,"
            + " registration_date_time, blocking) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_USER = "UPDATE user SET user.login=?,"
            + " user.password=?, user.role=?, user.email=?, user.avatar=?,"
            + " user.first_name=?, user.second_name=?, user.mobile_phone=?,"
            + " user.registration_date_time=?, user.blocking=? WHERE user.id=?";
    private static final String DELETE_USER = "DELETE FROM user WHERE id=?";

    private static final String GET_USER = "SELECT user.id, user.login,"
            + " user.password, user.role, user.email, user.avatar,"
            + " user.first_name, user.second_name, user.mobile_phone,"
            + " user.registration_date_time, user.blocking FROM user WHERE id = ?";

    private static final String GET_USER_BY_LOGIN_AND_PASSWORD = "SELECT user.id,"
            + " user.login, user.password, user.role, user.email, user.avatar,"
            + " user.first_name, user.second_name, user.mobile_phone,"
            + " user.registration_date_time, user.blocking FROM user WHERE user.login = ?"
            + " AND user.password = ?";
    private static final String GET_ALL_USERS = "SELECT user.id, user.login,"
            + " user.password, user.role, user.email, user.avatar,"
            + " user.first_name, user.second_name, user.mobile_phone,"
            + " user.registration_date_time, user.blocking FROM user ORDER BY id LIMIT ? OFFSET ?";
    private static final String GET_AMOUNT_OF_ALL_USERS_BY_ROLE = "SELECT"
            + " COUNT(user.role) FROM user WHERE user.role = ?";
    private static final String GET_AMOUNT_OF_ALL_USERS_BY_FIRST_NAME_AND_ROLE =
            "SELECT COUNT(user.role) FROM user WHERE user.first_name = ?"
                    + " AND user.role = ?";
    private static final String GET_AMOUNT_OF_ALL_USERS_BY_EMAIL =
            "SELECT COUNT(user.email) FROM user WHERE user.email = ?";
    private static final String GET_ALL_USERS_BY_ROLE =
            "SELECT user.id, user.login, user.password, user.role, user.email,"
            + " user.avatar, user.first_name, user.second_name,"
            + " user.mobile_phone, user.registration_date_time, user.blocking FROM user"
            + " WHERE user.role = ? ORDER BY id LIMIT ? OFFSET ?";
    private static final String GET_ALL_USERS_BY_FIRST_AND_SECOND_NAME =
            "SELECT user.id, user.login, user.password, user.role, user.email,"
            + " user.avatar, user.first_name, user.second_name,"
            + " user.mobile_phone, user.registration_date_time, user.blocking FROM user"
            + " WHERE (user.first_name = ? AND user.second_name = ?) ORDER BY id";

    private static final String GET_ALL_ACTIVE_USERS =
            "SELECT user.id, user.login, user.password, user.role, user.email," +
            " user.avatar, user.first_name, user.second_name," +
            " user.mobile_phone, user.registration_date_time, user.blocking" +
            " FROM user WHERE user.blocking = false ORDER BY id LIMIT ? OFFSET ?";

    public UserDaoImpl(final Connection newConnection) {
        super(newConnection);
    }

    @Override
    public int create(final User newUser) throws PersistentException {
        if (newUser == null) {
            return 0;
        }
        try (PreparedStatement preparedStatement = getConnection()
            .prepareStatement(ADD_USER, PreparedStatement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, newUser.getLogin());
            preparedStatement.setString(2, newUser.getPassword());
            preparedStatement.setInt(3, newUser.getRole().getOrdinal());
            preparedStatement.setString(4, newUser.getEmail());
            preparedStatement.setString(5, newUser.getPathToAvatar());
            preparedStatement.setString(6, newUser.getFirstName());
            preparedStatement.setString(7, newUser.getSecondName());
            preparedStatement.setInt(8, newUser.getMobilePhone());
            //TODO check cast
            preparedStatement.setDate(9, newUser.getRegistrationDate());
            preparedStatement.setBoolean(10, newUser.getBlocking());
            preparedStatement.executeUpdate();
            //?
            try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1);
                }
            } catch (SQLException newE) {
                LOGGER.log(Level.WARN, newE.getMessage(), newE);
                throw new PersistentException("Could not get generated keys!\n"
                    + newE.getMessage(), newE);
            }
            //return -1;
        } catch (SQLException newE) {
            LOGGER.log(Level.WARN, newE.getMessage(), newE);
            throw new PersistentException("Fail in adding row!\n"
                    + newE.getMessage(), newE);
        }
        return 0;
    }

    @Override
    public boolean update(final User newUser) throws PersistentException {
        try (PreparedStatement preparedStatement = getConnection()
                .prepareStatement(UPDATE_USER)) {
            preparedStatement.setString(1, newUser.getLogin());
            preparedStatement.setString(2, newUser.getPassword());
            preparedStatement.setInt(3, newUser.getRole().getOrdinal());
            preparedStatement.setString(4, newUser.getEmail());
            preparedStatement.setString(5, newUser.getPathToAvatar());
            preparedStatement.setString(6, newUser.getFirstName());
            preparedStatement.setString(7, newUser.getSecondName());
            preparedStatement.setInt(8, newUser.getMobilePhone());
            //TODO check cast
            preparedStatement.setDate(9, newUser.getRegistrationDate());
            preparedStatement.setBoolean(10, newUser.getBlocking());
            preparedStatement.setLong(11, newUser.getId());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException newE) {
            LOGGER.log(Level.WARN, newE);
            throw new PersistentException("Fail in updating user!\n"
                    + newE.getMessage(), newE);
        }
    }

    @Override
    public boolean delete(final User user) throws PersistentException {
        try (PreparedStatement preparedStatement = getConnection()
                .prepareStatement(DELETE_USER)) {
            preparedStatement.setLong(1, user.getId());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException newE) {
            LOGGER.log(Level.WARN, newE.getMessage(), newE);
            throw new PersistentException(newE.getMessage(), newE);
        }
    }

    //+
    @Override
    public User get() throws PersistentException {
        User user = null;
        try (PreparedStatement preparedStatement = getConnection()
                .prepareStatement(GET_USER)) {
            preparedStatement.setLong(1, 1);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    user = getUser(resultSet);
                }
            }
        } catch (SQLException newE) {
            LOGGER.log(Level.WARN, newE.getMessage(), newE);
            throw new PersistentException(newE.getMessage(), newE);
        }
        return user;
    }

    //+
    @Override
    public User get(final long userId) throws PersistentException {
        User user = null;
        try (PreparedStatement preparedStatement = getConnection()
                .prepareStatement(GET_USER)) {
            preparedStatement.setLong(1, userId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    user =  getUser(resultSet);
                }
            }
        } catch (SQLException newE) {
            LOGGER.log(Level.WARN, newE.getMessage(), newE);
            throw new PersistentException(newE.getMessage(), newE);
        }
        return user;
    }

    //+
    @Override
    public User get(final String login, final String password)
            throws PersistentException {
        User user = null;
        try (PreparedStatement preparedStatement = getConnection()
                .prepareStatement(GET_USER_BY_LOGIN_AND_PASSWORD)) {
            preparedStatement.setNString(1, login);
            preparedStatement.setNString(2, password);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    user = getUser(resultSet);
                }
            }
        } catch (SQLException newE) {
            LOGGER.log(Level.WARN, newE.getMessage(), newE);
            throw new PersistentException(newE.getMessage(), newE);
        }
        return user;
    }

    //+
    @Override
    public List<User> getAll(final int offset, final int limit)
            throws PersistentException {
        List<User> users = new LinkedList<>();
        try (PreparedStatement preparedStatement = getConnection()
                .prepareStatement(GET_ALL_USERS)) {
            preparedStatement.setInt(1, limit);
            preparedStatement.setInt(2, offset);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    users.add(getUser(resultSet));
                }
            }
            return users;
        } catch (SQLException newE) {
            LOGGER.log(Level.WARN, newE.getMessage(), newE);
            throw new PersistentException(newE.getMessage(), newE);
        }
    }

    @Override
    public boolean delete(final long userId) throws PersistentException {
        try (PreparedStatement preparedStatement = getConnection()
                .prepareStatement(DELETE_USER)) {
            preparedStatement.setLong(1, userId);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException newE) {
            LOGGER.log(Level.WARN, newE.getMessage(), newE);
            throw new PersistentException(newE.getMessage(), newE);
        }
    }

    //+
    @Override
    public int getAmountOfAllUsersByRole(final Role role)
            throws PersistentException {
        try (PreparedStatement preparedStatement = getConnection()
                .prepareStatement(GET_AMOUNT_OF_ALL_USERS_BY_ROLE)) {
            preparedStatement.setInt(1, role.getOrdinal());
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                resultSet.next();
                return resultSet.getInt(1);
            }
        } catch (SQLException newE) {
            LOGGER.log(Level.WARN, newE.getMessage(), newE);
            throw new PersistentException(newE.getMessage(), newE);
        }
    }

    //+
    @Override
    public int getAmountOfAllUsersByFirstNameAndRole(final String firstName,
                                                     final Role role)
            throws PersistentException {
        try (PreparedStatement preparedStatement = getConnection()
                .prepareStatement(GET_AMOUNT_OF_ALL_USERS_BY_FIRST_NAME_AND_ROLE)) {
            preparedStatement.setNString(1, firstName);
            preparedStatement.setInt(2, role.getOrdinal());
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                resultSet.next();
                return resultSet.getInt(1);
            }
        } catch (SQLException newE) {
            LOGGER.log(Level.WARN, newE.getMessage(), newE);
            throw new PersistentException(newE.getMessage(), newE);
        }
    }

    //+
    @Override
    public int getAmountOfAllUsersByEmail(final String email) throws PersistentException {
        try (PreparedStatement preparedStatement = getConnection()
                .prepareStatement(GET_AMOUNT_OF_ALL_USERS_BY_EMAIL)) {
            preparedStatement.setString(1, email);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                resultSet.next();
                return resultSet.getInt(1);
            }
        } catch (SQLException newE) {
            LOGGER.log(Level.WARN, newE.getMessage(), newE);
            throw new PersistentException(newE.getMessage(), newE);
        }
    }

    //+
    @Override
    public List<User> getAllUsersByRole(final Role newRole, int offset, int limit)
            throws PersistentException {
        List<User> users = new LinkedList<>();
        try (PreparedStatement preparedStatement = getConnection()
                .prepareStatement(GET_ALL_USERS_BY_ROLE)) {
            preparedStatement.setInt(1, newRole.getOrdinal());
            preparedStatement.setInt(2, limit);
            preparedStatement.setInt(3, offset);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    users.add(getUser(resultSet));
                }
            }
            return users;
        } catch (SQLException newE) {
            LOGGER.log(Level.WARN, newE.getMessage(), newE);
            throw new PersistentException(newE.getMessage(), newE);
        }
    }

    //+
    @Override
    public List<User> getAllUsersByFirstAndSecondName(final String firstName,
                                                      final String secondName)
            throws PersistentException {
        List<User> users = new LinkedList<>();
        try (PreparedStatement preparedStatement = getConnection()
                .prepareStatement(GET_ALL_USERS_BY_FIRST_AND_SECOND_NAME)) {
            preparedStatement.setNString(1, firstName);
            preparedStatement.setNString(2, secondName);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    users.add(getUser(resultSet));
                }
            }
            return users;
        } catch (SQLException newE) {
            LOGGER.log(Level.WARN, newE.getMessage(), newE);
            throw new PersistentException(newE.getMessage(), newE);
        }
    }

    //+
    @Override
    public List<User> getAllActiveUsers(final int offset, final int limit)
            throws PersistentException {
        List<User> users = new LinkedList<>();
        try (PreparedStatement preparedStatement = getConnection()
                .prepareStatement(GET_ALL_ACTIVE_USERS)) {
            preparedStatement.setInt(1, limit);
            preparedStatement.setInt(2, offset);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    users.add(getUser(resultSet));
                }
            }
            return users;
        } catch (SQLException newE) {
            LOGGER.log(Level.WARN, newE.getMessage(), newE);
            throw new PersistentException(newE.getMessage(), newE);
        }
    }

    private User getUser(final ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong(1);
        String login = resultSet.getNString("login");
        String password = resultSet.getNString("password");
        Role role = Role.valueOf(resultSet.getInt("role"));
        String email = resultSet.getNString("email");
        String avatar = resultSet.getNString("avatar");
        String name = resultSet.getNString("first_name");
        String surname = resultSet.getNString("second_name");
        int mobilePhone = resultSet.getInt("mobile_phone");
        Date registrationDateTime = resultSet.getDate("registration_date_time");
        boolean blocking = resultSet.getBoolean("blocking");
        return new User(id, login, password, role, email, avatar,
                name, surname, mobilePhone, registrationDateTime,
                blocking);
    }
}