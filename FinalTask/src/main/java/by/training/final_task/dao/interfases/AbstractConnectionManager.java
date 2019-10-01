package by.training.final_task.dao.interfases;


import by.training.final_task.dao.PersistentException;

import java.sql.Connection;

/**
 * Abstract class that subclasses are intended to provide
 * connection manipulation methods.
 * Used by service layer to provide connection to DAOs.
 */
public interface AbstractConnectionManager extends AutoCloseable {
    void disableAutoCommit() throws PersistentException;
    void enableAutoCommit() throws PersistentException;
    void commitChange() throws PersistentException;
    void rollbackChange() throws PersistentException;
    void close() throws PersistentException;
    Connection getConnection();
}
