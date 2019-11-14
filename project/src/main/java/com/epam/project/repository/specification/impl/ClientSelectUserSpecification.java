package com.epam.project.repository.specification.impl;

import com.epam.project.connection.ProxyConnection;
import com.epam.project.repository.specification.Specification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ClientSelectUserSpecification implements Specification {
    private static final String SELECT_USER = "SELECT id, role.role, login, password, email, cash, status, bikeId" +
            " FROM client JOIN role ON client.role = role.id WHERE role.role = ?";
    private static Logger Logger = LogManager.getLogger();

    @Override
    public PreparedStatement specify(ProxyConnection connection) throws SQLException {
        Logger.debug("find client who is user");
        PreparedStatement statement = connection.prepareStatement(SELECT_USER);
        statement.setString(1, "user");
        return statement;
    }
}
