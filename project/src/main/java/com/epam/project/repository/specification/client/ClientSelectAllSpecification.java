package com.epam.project.repository.specification.client;

import com.epam.project.connection.ProxyConnection;
import com.epam.project.repository.specification.QuerySpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ClientSelectAllSpecification implements QuerySpecification {
    private static final String SELECT_ALL = "SELECT client.id, role.name, login, password, email, cash, status, bike_id" +
            " FROM client JOIN role ON client.role = role.id";
    private static Logger Logger = LogManager.getLogger();

    @Override
    public PreparedStatement specify(ProxyConnection connection) throws SQLException {
        Logger.debug("select all clients");
         return connection.prepareStatement(SELECT_ALL);
    }
}
