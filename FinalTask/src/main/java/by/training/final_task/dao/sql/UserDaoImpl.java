package by.training.final_task.dao.sql;

import by.training.final_task.dao.interfases.AbstractConnectionManager;
import by.training.final_task.dao.interfases.UserDAO;
import by.training.final_task.entity.Role;
import by.training.final_task.entity.User;
import by.training.final_task.dao.PersistentException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class UserDaoImpl extends AbstractDao<User> implements UserDAO {

    private static final Logger LOGGER = LogManager.getLogger();

    private static final String ADD_USER = "INSERT INTO user (login, password,"
            + " role, email, avatar, first_name, second_name, mobile_phone,"
            + " registration_date_time, blocking)"
            + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_USER = "UPDATE user SET user.login=?,"
            + " user.password=?, user.role=?, user.email=?, user.avatar=?,"
            + " user.first_name=?, user.second_name=?, user.mobile_phone=?,"
            + " user.registration_date_time=?, user.blocking=? WHERE user.id=?";
    private static final String UPDATE_USER_STATUS =
            "UPDATE user SET blocking = true WHERE user.id=?";
    private static final String GET_USER = "SELECT user.id, user.login,"
            + " user.password, user.role, user.email, user.avatar,"
            + " user.first_name, user.second_name, user.mobile_phone,"
            + " user.registration_date_time, user.blocking FROM user"
            + " WHERE id = ?";
    private static final String GET_USER_BY_LOGIN =
            "SELECT user.id, user.login,"
                    + "user.password, user.role, user.email, user.avatar,"
                    + " user.first_name,"
                    + "user.second_name, user.mobile_phone,"
                    + " user.registration_date_time, user.blocking FROM user"
                    + " WHERE user.login = ? AND user.blocking=false";
    private static final String GET_USER_BY_EMAIL =
            "SELECT user.id, user.login, user.password, user.role, user.email,"
                    + " user.avatar, user.first_name, user.second_name,"
                    + " user.mobile_phone, user.registration_date_time,"
                    + "user.blocking FROM user WHERE user.email = ? "
                    + "AND user.blocking=false";
    private static final String GET_ALL_USERS = "SELECT user.id, user.login,"
            + " user.password, user.role, user.email, user.avatar,"
            + " user.first_name, user.second_name, user.mobile_phone,"
            + " user.registration_date_time, user.blocking FROM user"
            + " WHERE user.blocking=false ORDER BY id LIMIT ? OFFSET ?";
    private static final String GET_AMOUNT_OF_ALL_USERS_BY_ROLE = "SELECT"
            + " COUNT(user.role) FROM user WHERE user.role = ?"
            + " AND user.blocking = false";
    private static final String GET_AMOUNT_OF_ALL_USERS_BY_FIRST_NAME_AND_ROLE =
            "SELECT COUNT(user.role) FROM user WHERE user.first_name = ?"
                    + " AND user.role = ?";
    private static final String
            GET_AMOUNT_OF_ALL_USERS_BY_FIRST_NAME_AND_SECOND_NAME =
            "SELECT COUNT(user.role) FROM user WHERE user.first_name = ?"
                    + " AND user.second_name = ? AND user.role = ?";
    private static final String GET_AMOUNT_OF_ALL_USERS_BY_EMAIL =
            "SELECT COUNT(user.email) FROM user WHERE user.email = ?"
                    + " AND user.blocking = false";
    private static final String GET_ALL_USERS_BY_ROLE =
            "SELECT user.id, user.login, user.password, user.role, user.email,"
                    + " user.avatar, user.first_name, user.second_name,"
                    + " user.mobile_phone, user.registration_date_time,"
                    + " user.blocking FROM user WHERE user.blocking=false"
                    + " AND user.role = ? ORDER BY id LIMIT ? OFFSET ?";
    private static final String GET_ALL_USERS_BY_NAME_AND_ROLE =
            "SELECT user.id, user.login, user.password, user.role, user.email,"
                    + " user.avatar, user.first_name, user.second_name,"
                    + " user.mobile_phone, user.registration_date_time,"
                    + " user.blocking FROM user WHERE user.blocking=false"
                    + " AND user.first_name = ?"
                    + " AND user.role = ? ORDER BY id LIMIT ? OFFSET ?";
    private static final String GET_ALL_USERS_BY_FIRST_AND_SECOND_NAME =
            "SELECT user.id, user.login, user.password, user.role, user.email,"
                    + " user.avatar, user.first_name, user.second_name,"
                    + " user.mobile_phone, user.registration_date_time,"
                    + " user.blocking FROM user WHERE user.blocking=false"
                    + " AND (user.first_name = ? AND user.second_name = ?"
                    + " AND user.role=?) ORDER BY id";

    private static final String GET_ALL_ACTIVE_USERS =
            "SELECT user.id, user.login, user.password, user.role, user.email,"
                    + " user.avatar, user.first_name, user.second_name,"
                    + " user.mobile_phone, user.registration_date_time,"
                    + " user.blocking FROM user WHERE user.blocking = false"
                    + " ORDER BY id LIMIT ? OFFSET ?";

    public UserDaoImpl(final AbstractConnectionManager newConnection) {
        super(newConnection);
    }

    @Override
    public int create(final User newUser) throws PersistentException {
        if (newUser == null) {
            return 0;
        }
        try (PreparedStatement preparedStatement = getConnection()
                .prepareStatement(ADD_USER, PreparedStatement
                        .RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, newUser.getLogin());
            preparedStatement.setString(2, newUser.getPassword());
            preparedStatement.setInt(3, newUser.getRole()
                    .getOrdinal());
            preparedStatement.setString(4, newUser.getEmail());
            preparedStatement.setString(5, newUser
                    .getPathToAvatar());
            preparedStatement.setString(6, newUser
                    .getFirstName());
            preparedStatement.setString(7, newUser
                    .getSecondName());
            preparedStatement.setInt(8, newUser.getMobilePhone());
            preparedStatement.setDate(9, Date.valueOf(newUser
                    .getRegistrationDate()));
            preparedStatement.setBoolean(10, newUser
                    .getBlocking());
            preparedStatement.executeUpdate();
            try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1);
                }
            } catch (SQLException newE) {
                LOGGER.log(Level.WARN, newE.getMessage(), newE);
                throw new PersistentException("Could not get generated keys!\n"
                        + newE.getMessage(), newE);
            }
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
            preparedStatement.setInt(3, newUser.getRole()
                    .getOrdinal());
            preparedStatement.setString(4, newUser.getEmail());
            preparedStatement.setString(5, newUser
                    .getPathToAvatar());
            preparedStatement.setString(6, newUser
                    .getFirstName());
            preparedStatement.setString(7, newUser
                    .getSecondName());
            preparedStatement.setInt(8, newUser.getMobilePhone());
            preparedStatement.setDate(9, Date.valueOf(newUser
                    .getRegistrationDate()));
            preparedStatement.setBoolean(10, newUser
                    .getBlocking());
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
        LOGGER.log(Level.WARN, "Invalid operation to delete user.");
        throw new PersistentException("Invalid operation to delete user." +
                " You can change coupon status on unavailable.");
    }

    @Override
    public User get() throws PersistentException {
        return get(1);
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
                    user = takeUser(resultSet);
                }
            }
        } catch (SQLException newE) {
            LOGGER.log(Level.WARN, newE.getMessage(), newE);
            throw new PersistentException(newE.getMessage(), newE);
        }
        return user;
    }

    @Override
    public User getUserByLogin(final String login) throws PersistentException {
        User user = null;
        try (PreparedStatement preparedStatement = getConnection()
                .prepareStatement(GET_USER_BY_LOGIN)) {
            preparedStatement.setNString(1, login);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    user = takeUser(resultSet);
                }
            }
        } catch (SQLException newE) {
            LOGGER.log(Level.WARN, newE.getMessage(), newE);
            throw new PersistentException(newE.getMessage(), newE);
        }
        return user;
    }

    @Override
    public User getUserByEmail(final String email) throws PersistentException {
        User user = null;
        try (PreparedStatement preparedStatement = getConnection()
                .prepareStatement(GET_USER_BY_EMAIL)) {
            preparedStatement.setNString(1, email);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    user = takeUser(resultSet);
                }
            }
        } catch (SQLException newE) {
            LOGGER.log(Level.WARN, newE.getMessage(), newE);
            throw new PersistentException(newE.getMessage(), newE);
        }
        return user;
    }

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
                    User user = protectPassword(takeUser(resultSet));
                    users.add(user);
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
        LOGGER.log(Level.WARN, "Invalid operation to delete user.");
        throw new PersistentException("Invalid operation to delete user." +
                " You can change coupon status on unavailable.");
    }

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

    @Override
    public int getAmountOfAllUsersByFirstNameAndRole(final String firstName,
                                                     final Role role)
            throws PersistentException {
        try (PreparedStatement preparedStatement = getConnection()
                .prepareStatement(
                        GET_AMOUNT_OF_ALL_USERS_BY_FIRST_NAME_AND_ROLE)) {
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

    @Override
    public int getAmountOfAllUsersByFirstAndSecondName(final String firstName,
                                                       final String secondName,
                                                       final Role role)
            throws PersistentException {
        try (PreparedStatement preparedStatement = getConnection()
                .prepareStatement(
                        GET_AMOUNT_OF_ALL_USERS_BY_FIRST_NAME_AND_SECOND_NAME)) {
            preparedStatement.setNString(1, firstName);
            preparedStatement.setNString(2, secondName);
            preparedStatement.setInt(3, role.getOrdinal());
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                resultSet.next();
                return resultSet.getInt(1);
            }
        } catch (SQLException newE) {
            LOGGER.log(Level.WARN, newE.getMessage(), newE);
            throw new PersistentException(newE.getMessage(), newE);
        }
    }

    @Override
    public int getAmountOfAllUsersByEmail(final String email)
            throws PersistentException {
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

    @Override
    public boolean updateUserState(final long id) throws PersistentException {
        try (PreparedStatement preparedStatement = getConnection()
                .prepareStatement(UPDATE_USER_STATUS)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException newE) {
            LOGGER.log(Level.WARN, newE);
            throw new PersistentException("Fail in updating user status!\n"
                    + newE.getMessage(), newE);
        }
    }

    @Override
    public List<User> getAllUsersByRole(final Role newRole, int offset,
                                        int limit)
            throws PersistentException {
        List<User> users = new LinkedList<>();
        try (PreparedStatement preparedStatement = getConnection()
                .prepareStatement(GET_ALL_USERS_BY_ROLE)) {
            preparedStatement.setInt(1, newRole.getOrdinal());
            preparedStatement.setInt(2, limit);
            preparedStatement.setInt(3, offset);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    User user = protectPassword(takeUser(resultSet));
                    users.add(user);
                }
            }
            return users;
        } catch (SQLException newE) {
            LOGGER.log(Level.WARN, newE.getMessage(), newE);
            throw new PersistentException(newE.getMessage(), newE);
        }
    }

    @Override
    public List<User> getAllUsersByRoleAndName(final String name,
                                               final Role role,
                                               final int offset,
                                               final int limit)
            throws PersistentException {
        List<User> users = new LinkedList<>();
        try (PreparedStatement preparedStatement = getConnection()
                .prepareStatement(GET_ALL_USERS_BY_NAME_AND_ROLE)) {
            preparedStatement.setNString(1, name);
            preparedStatement.setInt(2, role.getOrdinal());
            preparedStatement.setInt(3, limit);
            preparedStatement.setInt(4, offset);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    User user = protectPassword(takeUser(resultSet));
                    users.add(user);
                }
            }
            return users;
        } catch (SQLException newE) {
            LOGGER.log(Level.WARN, newE.getMessage(), newE);
            throw new PersistentException(newE.getMessage(), newE);
        }
    }

    @Override
    public List<User> getAllUsersByFirstAndSecondName(final String firstName,
                                                      final String secondName,
                                                      final Role role)
            throws PersistentException {
        List<User> users = new LinkedList<>();
        try (PreparedStatement preparedStatement = getConnection()
                .prepareStatement(GET_ALL_USERS_BY_FIRST_AND_SECOND_NAME)) {
            preparedStatement.setNString(1, firstName);
            preparedStatement.setNString(2, secondName);
            preparedStatement.setInt(3, role.getOrdinal());
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    User user = protectPassword(takeUser(resultSet));
                    users.add(user);
                }
            }
            return users;
        } catch (SQLException newE) {
            LOGGER.log(Level.WARN, newE.getMessage(), newE);
            throw new PersistentException(newE.getMessage(), newE);
        }
    }

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
                    User user = protectPassword(takeUser(resultSet));
                    users.add(user);
                }
            }
            return users;
        } catch (SQLException newE) {
            LOGGER.log(Level.WARN, newE.getMessage(), newE);
            throw new PersistentException(newE.getMessage(), newE);
        }
    }

    private User protectPassword(final User user) {
        user.setPassword("");
        return user;
    }

    private User takeUser(final ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong(1);
        String login = resultSet.getNString("login");
        String password = resultSet.getNString("password");
        Role role = Role.valueOf(resultSet.getInt("role"));
        String email = resultSet.getNString("email");
        String avatar = resultSet.getNString("avatar");
        String name = resultSet.getNString("first_name");
        String surname = resultSet.getNString("second_name");
        int mobilePhone = resultSet.getInt("mobile_phone");
        Date registrationDateTime = resultSet
                .getDate("registration_date_time");
        boolean blocking = resultSet.getBoolean("blocking");
        return new User(id, login, password, role, email, avatar,
                name, surname, mobilePhone, registrationDateTime.toLocalDate(),
                blocking);
    }
}