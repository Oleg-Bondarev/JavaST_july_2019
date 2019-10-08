package by.training.final_task.dao.pool;

import by.training.final_task.dao.PersistentException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.locks.ReentrantLock;

public final class ConnectionPool {
    private static final Logger LOGGER = LogManager.getLogger();

    //TODO hardCode
    private String url = "jdbc:mysql://localhost:3306/stock_gift_coupons_db?serverTimezone=UTC";
    private String login = "root";
    private String password = "root";
    private int connectionTimeLimit = 0;
    private int maxCountConnection = 1000;
    private ReentrantLock classLocker = new ReentrantLock();
    private static ConnectionPool INSTANCE = null;
    private Queue<PooledConnection> freeConnections = new LinkedList<>();
    private Set<PooledConnection> usedConnections = new LinkedHashSet<>();

    public static ConnectionPool getInstance() {
        ReentrantLock lock = new ReentrantLock();
        if (INSTANCE == null) {
            lock.lock();
            try {
                INSTANCE = new ConnectionPool();
            } finally {
                lock.unlock();
            }
        }
        return INSTANCE;
    }

    public PooledConnection getConnection() throws PersistentException {
        PooledConnection connection = null;
        while (connection == null) {
            try {
                if (!freeConnections.isEmpty()) {
                    classLocker.lock();
                    connection = freeConnections.poll();
                    classLocker.unlock();
                    if (!connection.isValid(connectionTimeLimit)) {
                        try {
                            connection.getConnection().close();
                        } catch (SQLException newE) {
                            LOGGER.log(Level.WARN, newE.getMessage(), newE);
                            throw new PersistentException(newE.getMessage(), newE);
                        }
                        connection = null;
                    }
                } else if (usedConnections.size() < maxCountConnection) {
                    connection = createNewConnection();
                } else {
                    LOGGER.log(Level.ERROR, "Database connection limit" +
                            " exceeded.");
                    throw new PersistentException();
                }
            } catch (SQLException newE) {
                LOGGER.log(Level.ERROR, "Cannot connect to database.", newE);
                throw new PersistentException(newE.getMessage(), newE);
            }
        }
        usedConnections.add(connection);
        LOGGER.log(Level.DEBUG, "Connection was used. Available" +
                " connections: {}\nBusy connections: {}",
                freeConnections.size(),  usedConnections.size());
        return connection;
    }

    public void initialize(final String newDriverClass, final String newUrl,
                           final String newLogin, final String newPassword,
                           final int newStartConnections,
                           final int newMaxConnections, final int newTimeout)
            throws PersistentException {
        try {
            destroy();
            Class.forName(newDriverClass);
            url = newUrl;
            password = newPassword;
            login = newLogin;
            maxCountConnection = newMaxConnections;
            connectionTimeLimit = newTimeout;
            for (int i = 0; i < newStartConnections; i++) {
                freeConnections.add(createNewConnection());
            }
        } catch (ClassNotFoundException | SQLException newE) {
            LOGGER.log(Level.ERROR, "It is impossible to initialize" +
                    " connection pool.", newE);
            throw new PersistentException(newE.getMessage(), newE);
        }
    }

    private PooledConnection createNewConnection() throws PersistentException,
            SQLException {
        if ((usedConnections.size() + freeConnections.size()) < maxCountConnection) {
            return new PooledConnection(DriverManager
                    .getConnection(url, login, password));
        }
        throw new PersistentException("Cannot create connections more than" +
                " max amount of connections.");
    }

    public void freeConnection(final PooledConnection newConnection) {
        try {
            if (newConnection.isValid(connectionTimeLimit)) {
                newConnection.clearWarnings();
                newConnection.setAutoCommit(true);
                classLocker.lock();
                usedConnections.remove(newConnection);
                freeConnections.add(newConnection);
                classLocker.unlock();
                LOGGER.log(Level.DEBUG, "Connection cleared successful.");
            }
        } catch (SQLException newE) {
            LOGGER.log(Level.ERROR, "Could not clear connection.", newE);
            try {
                newConnection.close();
            } catch (SQLException newE1) {
                LOGGER.log(Level.ERROR, "Could not clear connection.",
                        newE1);
                //TODO next?
            }
        }
    }

    public void destroy() {
        if ((freeConnections != null) && (usedConnections != null)) {
            classLocker.lock();
            usedConnections.addAll(freeConnections);
            freeConnections.clear();
            classLocker.unlock();
            for (PooledConnection connection : usedConnections) {
                try {
                    connection.getConnection().close();
                } catch (SQLException newE) {
                    LOGGER.log(Level.WARN, newE.getMessage(), newE);
                    //TODO next?
                }
            }
            usedConnections.clear();
        }
    }

    @Override
    protected void finalize() throws Throwable {
        destroy();
    }
}
