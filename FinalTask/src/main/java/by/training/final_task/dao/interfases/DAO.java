package by.training.final_task.dao.interfases;

import by.training.final_task.entity.Entity;
import by.training.final_task.exception.PersistentException;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * DAO interface.
 * */
public interface DAO<T extends Entity> {

    int create(T element) throws PersistentException;
    T get() throws PersistentException;
    T get(long id) throws PersistentException;
    List<T> getAll(int offset, int limit) throws PersistentException;
    boolean update(T element) throws PersistentException;
    boolean delete(T element) throws PersistentException;
    boolean delete(long id) throws PersistentException;

    default void close(Statement newStatement) throws PersistentException {
        try {
            newStatement.close();
        } catch (SQLException newE) {
            throw new PersistentException(newE);
        }
    }
}
