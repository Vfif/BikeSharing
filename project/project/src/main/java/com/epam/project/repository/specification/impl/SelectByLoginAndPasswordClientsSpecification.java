package com.epam.project.repository.specification.impl;

import com.epam.project.connection.ProxyConnection;
import com.epam.project.repository.specification.Specification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SelectByLoginAndPasswordClientsSpecification implements Specification {
    private static final String SELECT_BY_LOGIN_AND_PASSWORD =
            "SELECT role, login, password, email FROM client WHERE login=? AND password=?";
    private static Logger Logger = LogManager.getLogger();
    private String login;
    private String password;


    public SelectByLoginAndPasswordClientsSpecification(String login, String password) {
        this.login = login;
        this.password = password;
    }

    @Override
    public PreparedStatement specify(ProxyConnection connection) throws SQLException {
        Logger.debug("find client by login and password");
        PreparedStatement statement = connection.prepareStatement(SELECT_BY_LOGIN_AND_PASSWORD);
        statement.setString(1, login);
        statement.setString(2, password);
        return statement;
    }
}
