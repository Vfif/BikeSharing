package com.epam.project.connection;

import com.epam.project.exception.ConnectionPoolException;
import com.epam.project.exception.PropertiesFileNotFound;
import com.epam.project.resource.DatabaseManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

public final class ConnectionPool {
    private static final ReentrantLock lock = new ReentrantLock();
    private static Logger Logger = LogManager.getLogger();
    private static ConnectionPool instance;
    private static int poolSize;
    private static AtomicBoolean flag = new AtomicBoolean();
    private BlockingQueue<ProxyConnection> connections;

    private ConnectionPool() {
        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            poolSize = DatabaseManager.getInstance().getPoolSize();
        } catch (PropertiesFileNotFound e) {
            Logger.error("Cannot read size of connection pool from properties file", e);
        }
        connections = new ArrayBlockingQueue<>(poolSize);
        for (int i = 0; i < poolSize; i++) {
            ProxyConnection proxyConnection = new ProxyConnection();
            connections.offer(proxyConnection);
        }
    }

    public static ConnectionPool getInstance() {
        if (!flag.get()) {
            lock.lock();
            try {
                if (null == instance) {
                    instance = new ConnectionPool();
                    flag.set(true);
                }
            } finally {
                lock.unlock();
            }
        }
        return instance;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        if (instance != null) {
            throw new CloneNotSupportedException();
        }
        return super.clone();
    }

    public ProxyConnection getConnection() throws ConnectionPoolException {
        try {
            return connections.take();
        } catch (InterruptedException e) {
            Logger.error(e);
        }
        throw new ConnectionPoolException("Cannot get connection");
    }

    void releaseConnection(ProxyConnection connection) throws SQLException {
        if (!connection.getAutoCommit()) {
            connection.setAutoCommit(true);
        }
        connections.offer(connection);
    }

    public void destroyPool() throws ConnectionPoolException {
        try {
            for (int i = 0; i < poolSize; i++) {
                connections.take().closeConnection();
            }
            deregisterDrivers();
        } catch (SQLException | InterruptedException e) {
            Logger.error(e);
            throw new ConnectionPoolException(e);
        }
    }

    private void deregisterDrivers() throws SQLException {
        Enumeration<Driver> drivers = DriverManager.getDrivers();
        while (drivers.hasMoreElements()) {
            Driver driver = drivers.nextElement();
            DriverManager.deregisterDriver(driver);
        }
    }
}
