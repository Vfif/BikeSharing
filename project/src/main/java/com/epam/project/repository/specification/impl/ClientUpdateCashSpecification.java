package com.epam.project.repository.specification.impl;

import com.epam.project.connection.ProxyConnection;
import com.epam.project.repository.specification.Specification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ClientUpdateCashSpecification implements Specification {
    private static final String UPDATE = "UPDATE client SET cash = ? WHERE login = ?";
    private static Logger Logger = LogManager.getLogger();
    private double cash;
    private String login;

    public ClientUpdateCashSpecification(double cash, String login) {
        this.cash = cash;
        this.login = login;
    }

    @Override
    public PreparedStatement specify(ProxyConnection connection) throws SQLException {
        Logger.debug("update client");
        PreparedStatement statement = connection.prepareStatement(UPDATE);
        statement.setDouble(1, cash);
        statement.setString(2, login);
        return statement;
    }
}
