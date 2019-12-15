package com.epam.project.repository.specification.client;

import com.epam.project.connection.ProxyConnection;
import com.epam.project.repository.specification.QuerySpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ClientSelectByLoginSpecification implements QuerySpecification {
    private static final String SELECT_BY_LOGIN =
            "SELECT client.id, role.name, login, password, email, cash, status, bike_id " +
                    "FROM client JOIN role ON client.role = role.id WHERE login=?";
    private static Logger Logger = LogManager.getLogger();
    private String login;

    public ClientSelectByLoginSpecification(String login) {
        this.login = login;
    }

    @Override
    public PreparedStatement specify(ProxyConnection connection) throws SQLException {
        Logger.debug("find client by login");
        PreparedStatement statement = connection.prepareStatement(SELECT_BY_LOGIN);
        statement.setString(1, login);
        return statement;
    }
}
