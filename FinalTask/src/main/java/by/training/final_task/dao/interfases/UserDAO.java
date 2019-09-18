package by.training.final_task.dao.interfases;

import by.training.final_task.entity.Role;
import by.training.final_task.entity.User;
import by.training.final_task.exception.PersistentException;

import java.util.List;

public interface UserDAO  extends DAO<User> {
    User get(long userId) throws PersistentException;
    User get(String login, String password) throws PersistentException;
    List<User> getAll(int offset, int limit) throws PersistentException;
    List<User> getAllUsersByRole(Role role, int offset, int limit)
            throws PersistentException;
    List<User> getAllUsersByFirstAndSecondName(String firstName,
                                               String secondName)
            throws PersistentException;
    boolean delete(long userId) throws PersistentException;
    int getAmountOfAllUsersByRole(Role role) throws PersistentException;
    int getAmountOfAllUsersByFirstNameAndRole(String firstName, Role role)
            throws PersistentException;
    int getAmountOfAllUsersByEmail(String email) throws PersistentException;
}

