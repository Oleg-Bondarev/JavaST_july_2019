package by.training.final_task.service.implimentation;

import by.training.final_task.dao.interfases.UserDAO;
import by.training.final_task.dao.sql.DAOEnum;
import by.training.final_task.entity.Role;
import by.training.final_task.entity.User;
import by.training.final_task.dao.PersistentException;
import by.training.final_task.service.ServiceException;
import by.training.final_task.service.interfaces.UserService;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl extends ServiceImpl implements UserService {

    private static final Logger LOGGER = LogManager.getLogger();
    private UserDAO userDAO;

    UserServiceImpl(final Connection aliveConnection) {
        super(aliveConnection);
    }


    @Override
    public int create(final User newUser) throws ServiceException {
        try {
            newUser.setPassword(argonTwoHashAlgorithm(newUser.getPassword()));
            int userId = userDAO.create(newUser);
            commitAndChangeAutoCommit();
            return userId;
        } catch (PersistentException newE) {
            rollbackTransaction();
            throw new ServiceException(newE.getMessage(), newE);
        }
    }

    @Override
    public boolean update(final User newUser) throws ServiceException {
        try {
            prepareUserDao(DAOEnum.USER);
            boolean updateState = userDAO.update(newUser);
            commitAndChangeAutoCommit();
            return updateState;
        } catch (PersistentException newE) {
            rollbackTransaction();
            throw new ServiceException(newE.getMessage(), newE);
        }
    }

    @Override
    public boolean updateUserState(final long id) throws ServiceException {
        try {
            prepareUserDao(DAOEnum.USER);
            boolean updateState = userDAO.updateUserState(id);
            commitAndChangeAutoCommit();
            return updateState;
        } catch (PersistentException newE) {
            rollbackTransaction();
            throw new ServiceException(newE.getMessage(), newE);
        }
    }

    @Override
    public User get(final long id) throws ServiceException {
        userDAO = (UserDAO) createDAO(DAOEnum.USER);
        try {
            return userDAO.get(id);
        } catch (PersistentException newE) {
            throw new ServiceException(newE.getMessage(), newE);
        }
    }

    @Override
    public List<User> getAll(final int offset, final int limit)
            throws ServiceException {
        try {
            prepareUserDao(DAOEnum.USER);
            List<User> usersList = userDAO.getAll(offset, limit);
            commitAndChangeAutoCommit();
            return usersList;
        } catch (PersistentException newE) {
            rollbackTransaction();
            throw new ServiceException(newE.getMessage(), newE);
        }
    }

    @Override
    public List<User> getAllUsersByRole(final Role newRole, final int offset,
                                        final int limit)
            throws ServiceException {
        try {
            prepareUserDao(DAOEnum.USER);
            List<User> userList = userDAO.getAllUsersByRole(newRole, offset,
                                                            limit);
            commitAndChangeAutoCommit();
            return userList;
        } catch (PersistentException newE) {
            rollbackTransaction();
            throw new ServiceException(newE.getMessage(), newE);
        }
    }

    @Override
    public List<User> getAllUsersByFirstAndSecondName(final String firstName,
                                                      final String secondName)
            throws ServiceException {
        try {
            prepareUserDao(DAOEnum.USER);
            List<User> userList = userDAO
                    .getAllUsersByFirstAndSecondName(firstName, secondName);
            commitAndChangeAutoCommit();
            return userList;
        } catch (PersistentException newE) {
            rollbackTransaction();
            throw new ServiceException(newE.getMessage(), newE);
        }
    }

    @Override
    public List<User> getAllActiveUsers(final int offset, final int limit)
            throws ServiceException {
        try {
            prepareUserDao(DAOEnum.USER);
            List<User> userList = userDAO.getAllActiveUsers(offset, limit);
            commitAndChangeAutoCommit();
            return userList;
        } catch (PersistentException newE) {
            rollbackTransaction();
            throw new ServiceException(newE.getMessage(), newE);
        }
    }

    @Override
    public int getAmountOfAllUsersByRole(final Role newRole)
            throws ServiceException {
        try {
            prepareUserDao(DAOEnum.USER);
            int amount = userDAO.getAmountOfAllUsersByRole(newRole);
            commitAndChangeAutoCommit();
            return amount;
        } catch (PersistentException newE) {
            rollbackTransaction();
            throw new ServiceException(newE.getMessage(), newE);
        }
    }

    @Override
    public int getAmountOfAllUsersByFirstNameAndRole(final String firstName,
                                                     final Role role)
            throws ServiceException {
        try {
            prepareUserDao(DAOEnum.USER);
            int amount = userDAO
                    .getAmountOfAllUsersByFirstNameAndRole(firstName, role);
            commitAndChangeAutoCommit();
            return amount;
        } catch (PersistentException newE) {
            rollbackTransaction();
            throw new ServiceException(newE.getMessage(), newE);
        }
    }

    @Override
    public int getAmountOfAllUsersByEmail(final String email)
            throws ServiceException {
        try {
            prepareUserDao(DAOEnum.USER);
            int amount = userDAO.getAmountOfAllUsersByEmail(email);
            commitAndChangeAutoCommit();
            return amount;
        } catch (PersistentException newE) {
            rollbackTransaction();
            throw new ServiceException(newE.getMessage(), newE);
        }
    }

    private void prepareUserDao(final DAOEnum classType) throws ServiceException {
        try {
            connection.setAutoCommit(false);
            userDAO = (UserDAO) createDAO(classType);
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

    private String argonTwoHashAlgorithm(final String newPassword) {
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        String password = argon2.hash(4, 1024 * 1024, 4,
                newPassword);
        return password;
    }
}
