package by.training.final_task.dao.sql;

import by.training.final_task.dao.PersistentException;
import by.training.final_task.dao.interfases.AbstractConnectionManager;
import by.training.final_task.dao.pool.ConnectionPool;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionManager implements AbstractConnectionManager {

    private Connection connection;

    public ConnectionManager() throws PersistentException {
        try {
            connection = ConnectionPool.getInstance().getConnection();
            disableAutoCommit();
        } catch (PersistentException newE) {
            throw new PersistentException("Unable to get connection from"
                    + " the pool.", newE);
        }
    }

    @Override
    public void disableAutoCommit() throws PersistentException {
        try {
            if (connection.getAutoCommit()) {
                connection.setAutoCommit(false);
            }
        } catch (SQLException newE) {
            throw new PersistentException("Could not set auto commit"
                    + " ino false.", newE);
        }
    }

    @Override
    public void enableAutoCommit() throws PersistentException {
        try {
            if (!connection.getAutoCommit()) {
                connection.setAutoCommit(true);
            }
        } catch (SQLException newE) {
            throw new PersistentException("Could not set auto"
                    + " commit in true.", newE);
        }
    }

    @Override
    public void commitChange() throws PersistentException {
        try {
            connection.commit();
        } catch (SQLException newE) {
            throw new PersistentException("Could not commit", newE);
        }
    }

    @Override
    public void rollbackChange() throws PersistentException {
        try {
            connection.rollback();
        } catch (SQLException newE) {
            throw new PersistentException("Could not rollback.", newE);
        }
    }

    @Override
    public void close() throws PersistentException {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new PersistentException(
                    "Unable to return connection to the pool.", e);
        }
    }

    @Override
    public Connection getConnection() {
        return connection;
    }
}
