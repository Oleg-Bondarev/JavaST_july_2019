package by.training.final_task.service.implimentation;

import by.training.final_task.dao.interfases.AbstractConnectionManager;
import by.training.final_task.dao.interfases.DaoFactory;
import by.training.final_task.dao.interfases.UserDAO;
import by.training.final_task.dao.sql.ConnectionManager;
import by.training.final_task.entity.Role;
import by.training.final_task.entity.User;
import by.training.final_task.dao.PersistentException;
import by.training.final_task.service.ServiceException;
import by.training.final_task.service.interfaces.UserService;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;

import java.util.List;

public class UserServiceImpl extends AbstractService
        implements UserService {

    public UserServiceImpl(final DaoFactory newFactory) {
        super(newFactory);
    }

    @Override
    public int create(final User newUser) throws ServiceException {
        User dbUser = get(newUser.getLogin());
        if (dbUser != null) {
            throw new ServiceException("alreadyExistUser");
        }

        try (AbstractConnectionManager connectionManager =
                new ConnectionManager()) {
            try {
                UserDAO userDAO = getDaoFactory().createUserDAO(connectionManager);
                newUser.setPassword(argonTwoHashAlgorithm(newUser.getPassword()));
                int userId = userDAO.create(newUser);
                newUser.setId(userId);
                connectionManager.commitChange();
                return userId;
            } catch (PersistentException newE) {
                connectionManager.rollbackChange();
                throw new ServiceException(newE);
            }
        } catch (PersistentException newE) {
            throw new ServiceException(newE);
        }
    }

    @Override
    public boolean update(final User newUser) throws ServiceException {
        try (AbstractConnectionManager connectionManager = new ConnectionManager()) {
            try {
                UserDAO userDAO = getDaoFactory().createUserDAO(connectionManager);
                newUser.setPassword(argonTwoHashAlgorithm(newUser.getPassword()));
                boolean statement = userDAO.update(newUser);
                connectionManager.commitChange();
                return statement;
            } catch (PersistentException newE) {
                connectionManager.rollbackChange();
                throw new ServiceException(newE);
            }
        } catch (PersistentException newE) {
            throw new ServiceException(newE);
        }
    }

    @Override
    public boolean updateUserState(final long id) throws ServiceException {
        try (AbstractConnectionManager connectionManager = new ConnectionManager()) {
            try {
                UserDAO userDAO = getDaoFactory().createUserDAO(connectionManager);
                boolean statement = userDAO.updateUserState(id);
                connectionManager.commitChange();
                return statement;
            } catch (PersistentException newE) {
                connectionManager.rollbackChange();
                throw new ServiceException(newE);
            }
        } catch (PersistentException newE) {
            throw new ServiceException(newE);
        }
    }

    @Override
    public User get(final long id) throws ServiceException {
        try (AbstractConnectionManager connectionManager = new ConnectionManager()) {
            try {
                UserDAO userDAO = getDaoFactory().createUserDAO(connectionManager);
                return userDAO.get(id);
            } catch (PersistentException newE) {
                connectionManager.rollbackChange();
                throw new ServiceException(newE.getMessage(), newE);
            }
        } catch (PersistentException newE) {
            throw new ServiceException(newE);
        }
    }

    @Override
    public User get(final String login) throws ServiceException {
        try (AbstractConnectionManager connectionManager = new ConnectionManager()) {
            try {
                UserDAO userDAO = getDaoFactory().createUserDAO(connectionManager);
                return userDAO.getUserByLogin(login);
            } catch (PersistentException newE) {
                connectionManager.rollbackChange();
                throw new ServiceException(newE.getMessage(), newE);
            }
        } catch (PersistentException newE) {
            throw new ServiceException(newE);
        }
    }

    @Override
    public User getUserByLoginAndPassword(final String login, final String password)
            throws ServiceException {
        try (AbstractConnectionManager connectionManager = new ConnectionManager()) {
            try {
                UserDAO userDAO = getDaoFactory().createUserDAO(connectionManager);
                return userDAO.getUserByLoginAndPassword(login, password);
            } catch (PersistentException newE) {
                connectionManager.rollbackChange();
                throw new ServiceException(newE.getMessage(), newE);
            }
        } catch (PersistentException newE) {
            throw new ServiceException(newE);
        }
    }

    @Override
    public List<User> getAll(final int offset, final int limit)
            throws ServiceException {
        try (AbstractConnectionManager connectionManager = new ConnectionManager()) {
            try {
                UserDAO userDAO = getDaoFactory().createUserDAO(connectionManager);
                return userDAO.getAll(offset, limit);
            } catch (PersistentException newE) {
                connectionManager.rollbackChange();
                throw new ServiceException(newE.getMessage(), newE);
            }
        } catch (PersistentException newE) {
            throw new ServiceException(newE);
        }
    }

    @Override
    public List<User> getAllUsersByRoleAndName(final String name, final Role role,
                                               final int offset, final int limit)
            throws ServiceException {
        try (AbstractConnectionManager connectionManager = new ConnectionManager()) {
            try {
                UserDAO userDAO = getDaoFactory().createUserDAO(connectionManager);
                return userDAO.getAllUsersByRoleAndName(name, role, offset, limit);
            } catch (PersistentException newE) {
                connectionManager.rollbackChange();
                throw new ServiceException(newE.getMessage(), newE);
            }
        } catch (PersistentException newE) {
            throw new ServiceException(newE);
        }
    }

    @Override
    public List<User> getAllUsersByRole(final Role newRole, final int offset,
                                        final int limit)
            throws ServiceException {
        try (AbstractConnectionManager connectionManager = new ConnectionManager()) {
            try {
                UserDAO userDAO = getDaoFactory().createUserDAO(connectionManager);
                return userDAO.getAllUsersByRole(newRole, offset, limit);
            } catch (PersistentException newE) {
                connectionManager.rollbackChange();
                throw new ServiceException(newE.getMessage(), newE);
            }
        } catch (PersistentException newE) {
            throw new ServiceException(newE);
        }
    }

    @Override
    public List<User> getAllUsersByFirstAndSecondName(final String firstName,
                                                      final String secondName)
            throws ServiceException {
        try (AbstractConnectionManager connectionManager = new ConnectionManager()) {
            try {
                UserDAO userDAO = getDaoFactory().createUserDAO(connectionManager);
                return userDAO.getAllUsersByFirstAndSecondName(firstName, secondName);
            } catch (PersistentException newE) {
                connectionManager.rollbackChange();
                throw new ServiceException(newE.getMessage(), newE);
            }
        } catch (PersistentException newE) {
            throw new ServiceException(newE);
        }
    }

    @Override
    public List<User> getAllActiveUsers(final int offset, final int limit)
            throws ServiceException {
        try (AbstractConnectionManager connectionManager = new ConnectionManager()) {
            try {
                UserDAO userDAO = getDaoFactory().createUserDAO(connectionManager);
                return userDAO.getAllActiveUsers(offset, limit);
            } catch (PersistentException newE) {
                connectionManager.rollbackChange();
                throw new ServiceException(newE.getMessage(), newE);
            }
        } catch (PersistentException newE) {
            throw new ServiceException(newE);
        }
    }

    @Override
    public int getAmountOfAllUsersByRole(final Role newRole)
            throws ServiceException {
        try (AbstractConnectionManager connectionManager = new ConnectionManager()) {
            try {
                UserDAO userDAO = getDaoFactory().createUserDAO(connectionManager);
                return userDAO.getAmountOfAllUsersByRole(newRole);
            } catch (PersistentException newE) {
                connectionManager.rollbackChange();
                throw new ServiceException(newE.getMessage(), newE);
            }
        } catch (PersistentException newE) {
            throw new ServiceException(newE);
        }
    }

    @Override
    public int getAmountOfAllUsersByFirstNameAndRole(final String firstName,
                                                     final Role role)
            throws ServiceException {
        try (AbstractConnectionManager connectionManager = new ConnectionManager()) {
            try {
                UserDAO userDAO = getDaoFactory().createUserDAO(connectionManager);
                return userDAO.getAmountOfAllUsersByFirstNameAndRole(firstName, role);
            } catch (PersistentException newE) {
                connectionManager.rollbackChange();
                throw new ServiceException(newE.getMessage(), newE);
            }
        } catch (PersistentException newE) {
            throw new ServiceException(newE);
        }
    }

    @Override
    public int getAmountOfAllUsersByEmail(final String email)
            throws ServiceException {
        try (AbstractConnectionManager connectionManager = new ConnectionManager()) {
            try {
                UserDAO userDAO = getDaoFactory().createUserDAO(connectionManager);
                return userDAO.getAmountOfAllUsersByEmail(email);
            } catch (PersistentException newE) {
                connectionManager.rollbackChange();
                throw new ServiceException(newE.getMessage(), newE);
            }
        } catch (PersistentException newE) {
            throw new ServiceException(newE);
        }
    }

    private String argonTwoHashAlgorithm(final String newPassword) {
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        String password = argon2.hash(4, 1024 * 1024, 4,
                newPassword);
        return password;
    }
}
