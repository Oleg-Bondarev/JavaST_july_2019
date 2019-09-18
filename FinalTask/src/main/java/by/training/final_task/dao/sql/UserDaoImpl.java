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

    UserDaoImpl(final Connection newConnection) {
        super(newConnection);
    }

    @Override
    public int create(final User newUser) throws PersistentException {
        if (newUser == null) {
            return 0;
        }
        try (PreparedStatement preparedStatement = getConnection()
            .prepareStatement(getResourceBundle().getString("addUserDAO"),
                    PreparedStatement.RETURN_GENERATED_KEYS)) {
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
                .prepareStatement(getResourceBundle().getString("updateUserDAO"))) {
            preparedStatement.setString(1, newUser.getLogin());
            preparedStatement.setString(2, newUser.getPassword());
            preparedStatement.setInt(3, newUser.getRole().getOrdinal());
            preparedStatement.setString(4, newUser.getEmail());
            preparedStatement.setString(5, newUser.getPathToAvatar());
            preparedStatement.setString(6, newUser.getFirstName());
            preparedStatement.setString(7, newUser.getSecondName());
            preparedStatement.setInt(8, newUser.getMobilePhone());
            //TODO check cast
            preparedStatement.setDate(9, (Date) newUser.getRegistrationDate());
            preparedStatement.setLong(10, newUser.getId());
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
                .prepareStatement(getResourceBundle().getString("deleteUserDAO"))) {
            preparedStatement.setLong(1, user.getId());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException newE) {
            LOGGER.log(Level.WARN, newE.getMessage(), newE);
            throw new PersistentException(newE.getMessage(), newE);
        }
    }

    @Override
    public User get() throws PersistentException {
        try (PreparedStatement preparedStatement = getConnection()
                .prepareStatement(getResourceBundle().getString("getUserDAO"))) {
            preparedStatement.setLong(1, 1);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    long id = resultSet.getLong(1);
                    String login = resultSet.getNString("login");
                    String password = resultSet.getNString("password");
                    Role role = Role.valueOf(resultSet.getInt(4));
                    String email = resultSet.getNString("email");
                    //??
                    String avatar = resultSet.getNString("avatar");
                    String name = resultSet.getNString("first_name");
                    String surname = resultSet.getNString("second_name");
                    int mobilePhone = resultSet.getInt(8);
                    Date registrationDateTime = resultSet.getDate("registration_date_time");
                    return new User(id, login, password, role, email, avatar,
                            name, surname, mobilePhone, registrationDateTime);
                }
            }
        } catch (SQLException newE) {
            LOGGER.log(Level.WARN, newE.getMessage(), newE);
            throw new PersistentException(newE.getMessage(), newE);
        }
        // TODO возвращать null???
        return null;
    }

    @Override
    public User get(final long userId) throws PersistentException {
        try (PreparedStatement preparedStatement = getConnection()
                .prepareStatement(getResourceBundle().getString("getUserByIdDAO"))) {
            preparedStatement.setLong(1, userId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                long id = resultSet.getLong(1);
                String login = resultSet.getNString("login");
                String password = resultSet.getNString("password");
                Role role = Role.values()[resultSet.getInt("role")];
                String email = resultSet.getNString("email");
                //avatar?
                String avatar = resultSet.getNString("avatar");
                String firstName = resultSet.getNString("first_name");
                String secondName = resultSet.getNString("second_name");
                int mobilePhone = resultSet.getInt("mobile_phone");
                Date registrationDateTime = resultSet.getDate("registration_date_time");
                return new User(id, login, password, role, email, avatar,
                        firstName, secondName, mobilePhone, registrationDateTime);
            }
        } catch (SQLException newE) {
            LOGGER.log(Level.WARN, newE.getMessage(), newE);
            throw new PersistentException(newE.getMessage(), newE);
        }
    }

    @Override
    public User get(final String login, final String password)
            throws PersistentException {
        try (PreparedStatement preparedStatement = getConnection()
                .prepareStatement(getResourceBundle()
                        .getString("getUserByUserLoginAndPasswordDAO"))) {
            preparedStatement.setNString(1, login);
            preparedStatement.setNString(2, password);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    long id = resultSet.getLong(1);
                    String userLogin = resultSet.getNString("login");
                    String userPassword = resultSet.getNString("password");
                    Role role = Role.valueOf(resultSet.getInt(4));
                    String email = resultSet.getNString("email");
                    //??
                    String avatar = resultSet.getNString("avatar");
                    String name = resultSet.getNString("first_name");
                    String surname = resultSet.getNString("second_name");
                    int mobilePhone = resultSet.getInt(8);
                    Date registrationDateTime = resultSet.getDate("registration_date_time");
                    return new User(id, userLogin, userPassword, role, email, avatar,
                            name, surname, mobilePhone, registrationDateTime);
                }
            }
        } catch (SQLException newE) {
            LOGGER.log(Level.WARN, newE.getMessage(), newE);
            throw new PersistentException(newE.getMessage(), newE);
        }
        //TODO возвращать null???
        return null;
    }

    @Override
    public List<User> getAll(final int offset, final int limit)
            throws PersistentException {
        List<User> users = new LinkedList<>();
        try (PreparedStatement preparedStatement = getConnection()
                .prepareStatement(getResourceBundle().getString("getAllUsersDAO"))) {
            preparedStatement.setInt(1, offset);
            preparedStatement.setInt(2, limit);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    long id = resultSet.getLong(1);
                    String login = resultSet.getNString("login");
                    String password = resultSet.getNString("password");
                    Role role = Role.valueOf(resultSet.getInt(4));
                    String email = resultSet.getNString("email");
                    //??
                    String avatar = resultSet.getNString("avatar");
                    String name = resultSet.getNString("first_name");
                    String surname = resultSet.getNString("second_name");
                    int mobilePhone = resultSet.getInt(8);
                    Date registrationDateTime = resultSet.getDate("registration_date_time");
                    users.add(new User(id, login, password, role, email, avatar,
                            name, surname, mobilePhone, registrationDateTime));
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
                .prepareStatement(getResourceBundle().getString("deleteUserDAO"))) {
            preparedStatement.setLong(1, userId);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException newE) {
            LOGGER.log(Level.WARN, newE.getMessage(), newE);
            throw new PersistentException(newE.getMessage(), newE);
        }
    }

    @Override
    public int getAmountOfAllUsersByRole(final Role role)
            throws PersistentException {
        try (PreparedStatement preparedStatement = getConnection()
                .prepareStatement(getResourceBundle().getString(
                        "getAmountOfAllUsersByRole"))) {
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
                .prepareStatement(getResourceBundle().getString(
                        "getAmountOfAllUsersByFirstNameAndRole"))) {
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
    public int getAmountOfAllUsersByEmail(final String email) throws PersistentException {
        try (PreparedStatement preparedStatement = getConnection()
                .prepareStatement(getResourceBundle().getString(
                        "getAmountOfAllUsersByEmailDAO"))) {
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
    public List<User> getAllUsersByRole(final Role newRole, int offset, int limit)
            throws PersistentException {
        List<User> users = new LinkedList<>();
        try (PreparedStatement preparedStatement = getConnection()
                .prepareStatement(getResourceBundle().getString(
                        "getAllUsersByRoleDAO"))) {
            preparedStatement.setInt(1, newRole.getOrdinal());
            preparedStatement.setInt(2, offset);
            preparedStatement.setInt(3, limit);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    long id = resultSet.getLong("id");
                    String login = resultSet.getNString("login");
                    String password = resultSet.getNString("password");
                    Role role = Role.valueOf(resultSet.getInt("role"));
                    String email = resultSet.getNString("email");
                    //нужно ли???
                    String avatar = resultSet.getNString("avatar");
                    String name = resultSet.getNString("first_name");
                    String surname = resultSet.getNString("second_name");
                    int mobilePhone = resultSet.getInt(8);
                    Date registrationDateTime = resultSet.getDate("registration_date_time");
                    users.add(new User(id, login, password, role, email, avatar,
                            name, surname, mobilePhone, registrationDateTime));
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
                                                      final String secondName)
            throws PersistentException {
        List<User> users = new LinkedList<>();
        try (PreparedStatement preparedStatement = getConnection()
                .prepareStatement(getResourceBundle().getString(
                        "getAllUsersByFirstAndSecondNameDAO"))) {
            preparedStatement.setNString(1, firstName);
            preparedStatement.setNString(2, secondName);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    long id = resultSet.getLong("id");
                    String login = resultSet.getNString("login");
                    String password = resultSet.getNString("password");
                    Role role = Role.valueOf(resultSet.getInt("role"));
                    String email = resultSet.getNString("email");
                    //нужно ли???
                    String avatar = resultSet.getNString("avatar");
                    String name = resultSet.getNString("first_name");
                    String surname = resultSet.getNString("second_name");
                    int mobilePhone = resultSet.getInt(8);
                    Date registrationDateTime = resultSet.getDate("registration_date_time");
                    users.add(new User(id, login, password, role, email, avatar,
                            name, surname, mobilePhone, registrationDateTime));
                }
            }
            return users;
        } catch (SQLException newE) {
            LOGGER.log(Level.WARN, newE.getMessage(), newE);
            throw new PersistentException(newE.getMessage(), newE);
        }
    }
}
