package com.epam.project.repository.specification.bike;

import com.epam.project.connection.ProxyConnection;
import com.epam.project.repository.specification.UpdateSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BikeUpdateNameSpecification implements UpdateSpecification {
    private static Logger Logger = LogManager.getLogger();
    private static final String UPDATE = "UPDATE bike SET name= ? WHERE id = ?";
    private String name;
    private int bikeId;

    public BikeUpdateNameSpecification(int bikeId, String name) {
        this.name = name;
        this.bikeId = bikeId;
    }

    @Override
    public PreparedStatement specify(ProxyConnection connection) throws SQLException {
        Logger.debug("update bike name");
        PreparedStatement statement = connection.prepareStatement(UPDATE);
        statement.setString(1, name);
        statement.setInt(2, bikeId);
        return statement;
    }
}