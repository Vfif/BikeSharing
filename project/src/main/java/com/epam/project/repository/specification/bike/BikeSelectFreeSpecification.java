package com.epam.project.repository.specification.bike;

import com.epam.project.connection.ProxyConnection;
import com.epam.project.repository.specification.QuerySpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BikeSelectFreeSpecification implements QuerySpecification {
    private static Logger Logger = LogManager.getLogger();
    private static final String SELECT =
            "SELECT status, id, name, cost, description, image, time, address, location  " +
                    "FROM bike WHERE time = 0 AND status = 0";

    @Override
    public PreparedStatement specify(ProxyConnection connection) throws SQLException {
        Logger.debug("select free not delete bikes");
        return connection.prepareStatement(SELECT);
    }
}
