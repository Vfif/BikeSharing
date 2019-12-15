package com.epam.project.repository.specification.bike;

import com.epam.project.connection.ProxyConnection;
import com.epam.project.repository.specification.QuerySpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BikeSelectByIdNotRentSpecification implements QuerySpecification {
    private static Logger Logger = LogManager.getLogger();
    private static final String SELECT_BY_ID =
            "SELECT status, id, name, cost, description, image, time, address, location FROM bike WHERE id=? AND time=0";
    private int id;

    public BikeSelectByIdNotRentSpecification(int id) {
        this.id = id;
    }

    @Override
    public PreparedStatement specify(ProxyConnection connection) throws SQLException {
        Logger.debug("select all bikes");
        PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID);
        statement.setInt(1, id);
        return statement;
    }
}
