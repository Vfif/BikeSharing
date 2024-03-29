package com.epam.project.repository.specification.client;

import com.epam.project.connection.ProxyConnection;
import com.epam.project.repository.specification.UpdateSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ClientUpdateStatusSpecification implements UpdateSpecification {
    private static final String UPDATE = "UPDATE client SET status = ? WHERE login = ?";
    private static Logger Logger = LogManager.getLogger();
    private boolean status;
    private String login;

    public ClientUpdateStatusSpecification(String login, boolean status) {
        this.status = status;
        this.login = login;
    }

    @Override
    public PreparedStatement specify(ProxyConnection connection) throws SQLException {
        Logger.debug("update client");
        PreparedStatement statement = connection.prepareStatement(UPDATE);
        statement.setBoolean(1, status);
        statement.setString(2, login);
        return statement;
    }
}
