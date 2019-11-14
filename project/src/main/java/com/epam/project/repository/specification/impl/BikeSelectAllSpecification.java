package com.epam.project.repository.specification.impl;

import com.epam.project.connection.ProxyConnection;
import com.epam.project.repository.specification.Specification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BikeSelectAllSpecification implements Specification {
    private static final String SELECT_ALL =
            "SELECT id, name, cost, description, image, rentTime, address, location  FROM bike WHERE rentTime = 0";
    private static Logger Logger = LogManager.getLogger();

    @Override
    public PreparedStatement specify(ProxyConnection connection) throws SQLException {
        Logger.debug("select all bikes");
        return connection.prepareStatement(SELECT_ALL);
    }
}
