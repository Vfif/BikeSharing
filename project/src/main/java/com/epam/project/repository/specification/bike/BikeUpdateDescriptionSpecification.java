package com.epam.project.repository.specification.bike;

import com.epam.project.connection.ProxyConnection;
import com.epam.project.repository.specification.UpdateSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BikeUpdateDescriptionSpecification  implements UpdateSpecification {
    private static Logger Logger = LogManager.getLogger();
    private static final String UPDATE = "UPDATE bike SET description= ? WHERE id = ?";
    private String description;
    private int bikeId;

    public BikeUpdateDescriptionSpecification(int bikeId, String description) {
        this.description = description;
        this.bikeId = bikeId;
    }

    @Override
    public PreparedStatement specify(ProxyConnection connection) throws SQLException {
        Logger.debug("update bike description");
        PreparedStatement statement = connection.prepareStatement(UPDATE);
        statement.setString(1, description);
        statement.setInt(2, bikeId);
        return statement;
    }
}