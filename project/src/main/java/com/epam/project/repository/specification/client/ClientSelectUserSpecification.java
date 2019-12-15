package com.epam.project.repository.specification.client;

import com.epam.project.connection.ProxyConnection;
import com.epam.project.repository.specification.QuerySpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ClientSelectUserSpecification implements QuerySpecification {
    private static final String SELECT_USER = "SELECT client.id, role.name, login, password, email, cash, status, bike_id" +
            " FROM client JOIN role ON client.role = role.id WHERE role.name = ?";
    private static Logger Logger = LogManager.getLogger();

    @Override
    public PreparedStatement specify(ProxyConnection connection) throws SQLException {
        Logger.debug("find client who is user");
        PreparedStatement statement = connection.prepareStatement(SELECT_USER);
        statement.setString(1, "user");
        return statement;
    }
}
