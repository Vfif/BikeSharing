package com.epam.project.repository.specification.bike;

import com.epam.project.connection.ProxyConnection;
import com.epam.project.repository.specification.UpdateSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BikeUpdateCostSpecification implements UpdateSpecification {
    private static Logger Logger = LogManager.getLogger();
    private static final String UPDATE = "UPDATE bike SET cost= ? WHERE id = ?";
    private int cost;
    private int bikeId;

    public BikeUpdateCostSpecification(int bikeId, int cost) {
        this.cost = cost;
        this.bikeId = bikeId;
    }

    @Override
    public PreparedStatement specify(ProxyConnection connection) throws SQLException {
        Logger.debug("update bike cost");
        PreparedStatement statement = connection.prepareStatement(UPDATE);
        statement.setInt(1, cost);
        statement.setInt(2, bikeId);
        return statement;
    }
}
