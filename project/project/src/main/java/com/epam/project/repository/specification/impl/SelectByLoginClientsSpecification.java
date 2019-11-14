package com.epam.project.repository.specification.impl;

import com.epam.project.connection.ProxyConnection;
import com.epam.project.repository.specification.Specification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SelectByLoginClientsSpecification implements Specification {
    private static final String SELECT_BY_LOGIN = "SELECT role, login, password, email FROM client WHERE login=?";
    private static Logger Logger = LogManager.getLogger();
    private String login;

    public SelectByLoginClientsSpecification(String login) {
        this.login = login;
    }

    @Override
    public PreparedStatement specify(ProxyConnection connection) throws SQLException {
        Logger.debug("find client where login = " + login);
        PreparedStatement statement = connection.prepareStatement(SELECT_BY_LOGIN);
        statement.setString(1, login);
        return statement;
    }
}