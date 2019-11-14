package com.epam.project.repository.specification.impl;

import com.epam.project.connection.ProxyConnection;
import com.epam.project.repository.specification.Specification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SelectAllClientsSpecification implements Specification {
    private static final String SELECT_ALL = "SELECT id, role, login, password, email FROM client";
    private static Logger Logger = LogManager.getLogger();

    @Override
    public PreparedStatement specify(ProxyConnection connection) throws SQLException {
        Logger.debug("select all clients");
         return connection.prepareStatement(SELECT_ALL);
    }
}
