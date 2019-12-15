package com.epam.project.connection;

import com.epam.project.exception.ConnectionPoolException;
import com.epam.project.exception.PropertiesFileNotFoundException;
import com.epam.project.resource.DatabaseManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Timer;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public final class ConnectionPool {
    private static Logger Logger = LogManager.getLogger();
    private static final ReentrantLock lock = new ReentrantLock();
    private static Condition condition = lock.newCondition();
    private static ConnectionPool instance;
    private static int poolSize;
    private static AtomicBoolean flag = new AtomicBoolean();
    private BlockingQueue<ProxyConnection> freeConnections;
    private ArrayList<ProxyConnection> givenConnections;

    private ConnectionPool() {
        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
        } catch (SQLException e) {
            Logger.error("Cannot register driver ", e);
        }
        try {
            poolSize = DatabaseManager.getInstance().getPoolSize();
        } catch (PropertiesFileNotFoundException e) {
            Logger.error("Cannot read size of connection pool from properties file", e);
        }
        freeConnections = new ArrayBlockingQueue<>(poolSize);
        givenConnections = new ArrayList<>();
        for (int i = 0; i < poolSize; i++) {
            ProxyConnection proxyConnection = new ProxyConnection();
            freeConnections.offer(proxyConnection);
        }
        Timer timer = new Timer(true);
        timer.schedule(new ConnectionPoolSupport(lock, condition, poolSize, freeConnections, givenConnections),
                TimeUnit.DAYS.toMillis(1), TimeUnit.DAYS.toMillis(1));
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
            if(lock.isLocked()){
                condition.await();
            }
            ProxyConnection connection = freeConnections.take();
            givenConnections.add(connection);
            return connection;
        } catch (InterruptedException e) {
            Logger.error(e);
        }
        throw new ConnectionPoolException("Cannot get connection");
    }

    void releaseConnection(ProxyConnection connection) throws SQLException {
        if (!connection.getAutoCommit()) {
            connection.setAutoCommit(true);
        }
        freeConnections.offer(connection);
        givenConnections.remove(connection);
    }

    public void destroyPool() throws ConnectionPoolException {
        try {
            for (int i = 0; i < poolSize; i++) {
                freeConnections.take().closeConnection();
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
