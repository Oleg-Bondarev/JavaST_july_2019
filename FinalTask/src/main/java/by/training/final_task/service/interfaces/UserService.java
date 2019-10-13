package by.training.final_task.service.interfaces;

import by.training.final_task.entity.Role;
import by.training.final_task.entity.User;
import by.training.final_task.service.ServiceException;

import java.util.List;

public interface UserService extends Service {

    int create(User newUser) throws ServiceException;

    boolean update(User newUser) throws ServiceException;

    boolean updateUserState(final long id) throws ServiceException;

    User get(long id) throws ServiceException;

    User get(String login) throws ServiceException;

    User getUserByLoginAndPassword(String login, String password)
            throws ServiceException;

    List<User> getAll(int offset, int limit) throws ServiceException;

    List<User> getAllUsersByRoleAndName(String name, Role role, int offset,
                                        int limit) throws ServiceException;

    List<User> getAllUsersByRole(final Role newRole, int offset, int limit)
            throws ServiceException;

    List<User> getAllUsersByFirstAndSecondName(final String firstName,
                                final String secondName) throws ServiceException;

    List<User> getAllActiveUsers(final int offset, final int limit)
            throws ServiceException;

    int getAmountOfAllUsersByRole(final Role newRole) throws ServiceException;

    int getAmountOfAllUsersByFirstNameAndRole(final String firstName,
                                final Role role) throws ServiceException;

    int getAmountOfAllUsersByEmail(final String email) throws ServiceException;

}
