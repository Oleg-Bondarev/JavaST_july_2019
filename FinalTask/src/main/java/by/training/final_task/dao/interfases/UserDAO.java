package by.training.final_task.dao.interfases;

import by.training.final_task.entity.Role;
import by.training.final_task.entity.User;
import by.training.final_task.dao.PersistentException;

import java.util.List;

public interface UserDAO  extends DAO<User> {
    User getUserByLoginAndPassword(String login, String password)
            throws PersistentException;
    User getUserByLogin(String login) throws PersistentException;
    List<User> getAllUsersByRole(Role role, int offset, int limit)
            throws PersistentException;
    List<User> getAllUsersByFirstAndSecondName(String firstName,
                                               String secondName)
            throws PersistentException;
    List<User> getAllActiveUsers(int limit, int offset) throws PersistentException;
    int getAmountOfAllUsersByRole(Role role) throws PersistentException;
    int getAmountOfAllUsersByFirstNameAndRole(String firstName, Role role)
            throws PersistentException;
    int getAmountOfAllUsersByEmail(String email) throws PersistentException;
    boolean updateUserState(long id) throws PersistentException;
}

