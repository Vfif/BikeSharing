package com.epam.project.connection;

import com.epam.project.exception.ConnectionPoolException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static org.testng.Assert.*;

public class ConnectionPoolTest {
    ProxyConnection connection;
    @Test
    public void getInstanceTest() {
        assertNotNull(ConnectionPool.getInstance());
    }

    @Test(expectedExceptions = CloneNotSupportedException.class)
    public void testClone1() throws CloneNotSupportedException {
        ConnectionPool.getInstance().clone();
    }

    @Test(dependsOnMethods = "getInstanceTest")
    public void getConnectionTest() {
        try {
            connection = ConnectionPool.getInstance().getConnection();
            assertNotNull(connection);
        } catch (ConnectionPoolException e) {
            fail();
        }
    }

    @Test(dependsOnMethods = "getConnectionTest")
    public void releaseConnectionTest() {
        try {
            ConnectionPool.getInstance().releaseConnection(connection);
        } catch (SQLException e) {
            fail();
        }
    }

    @AfterClass
    public void end() {
        try {
            ConnectionPool.getInstance().destroyPool();
        } catch (ConnectionPoolException e) {
            fail();
        }
    }
}