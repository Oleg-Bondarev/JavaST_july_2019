package by.training.final_task.dao.sql;

import java.sql.Connection;
import java.util.ResourceBundle;

abstract class BaseDaoImpl {
    private final String pathForProperty = "SQL_queries.properties";
    private Connection connection;
    private ResourceBundle resourceBundle;

    public BaseDaoImpl(final Connection newConnection) {
        connection = newConnection;
    }

    public void setConnection(final Connection newConnection) {
        connection = newConnection;
    }

    public Connection getConnection() {
        return connection;
    }

    public String getPathForProperty() {
        return pathForProperty;
    }

    public ResourceBundle getResourceBundle() {
        return resourceBundle;
    }
}
