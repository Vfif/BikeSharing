package com.epam.project.repository.specification.bike;

import com.epam.project.connection.ProxyConnection;
import com.epam.project.repository.specification.QuerySpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BikeSelectAllSpecification implements QuerySpecification {
    private static Logger Logger = LogManager.getLogger();
    private static final String SELECT_ALL =
            "SELECT status, id, name, cost, description, image, time, address, location FROM bike WHERE status = 0";

    @Override
    public PreparedStatement specify(ProxyConnection connection) throws SQLException {
        Logger.debug("select all bikes");
        return connection.prepareStatement(SELECT_ALL);
    }
}
