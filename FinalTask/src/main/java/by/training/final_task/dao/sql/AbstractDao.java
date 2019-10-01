package by.training.final_task.dao.sql;

import by.training.final_task.dao.interfases.AbstractConnectionManager;
import by.training.final_task.dao.interfases.DAO;
import by.training.final_task.entity.Entity;

import java.sql.Connection;

public abstract class AbstractDao<T extends Entity> implements DAO<T> {
    private Connection connection;
    AbstractDao(final AbstractConnectionManager newManager) {
        connection = newManager.getConnection();
    }

    public Connection getConnection() {
        return connection;
    }
}
