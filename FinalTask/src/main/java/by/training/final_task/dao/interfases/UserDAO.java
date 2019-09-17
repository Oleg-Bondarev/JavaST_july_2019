package by.training.final_task.dao.interfases;

import by.training.final_task.entity.User;
import by.training.final_task.exception.PersistentException;

import java.util.List;

public interface UserDAO  extends DAO<User> {
    User get(int userId) throws PersistentException;
    User get(String login, String password) throws PersistentException;
    List<User> getAll(int offset, int limit) throws PersistentException;
    boolean delete(int userId) throws PersistentException;
    int getAmountOfAllUsers() throws PersistentException;
    int getAmountOfAllUsersByFirstName(String firstName)
            throws PersistentException;
    //can be list?
    int getAmountOfAllUsersByMobilePhone(int phone) throws PersistentException;
    //can be list?
    int getAmountOfAllUsersByEmail(String email) throws PersistentException;
    List<User> getAllUsers(int offset, int limit) throws PersistentException;
    List<User> getAllUsersByFirstName(String firstName, int offset, int limit)
            throws PersistentException;
    List<User> getAllUsersBySecondName(String secondName)
            throws PersistentException;
}

