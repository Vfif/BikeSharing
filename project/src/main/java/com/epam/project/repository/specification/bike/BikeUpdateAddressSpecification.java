package com.epam.project.repository.specification.bike;

import com.epam.project.connection.ProxyConnection;
import com.epam.project.repository.specification.UpdateSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BikeUpdateAddressSpecification implements UpdateSpecification {
    private static Logger Logger = LogManager.getLogger();
    private static final String UPDATE = "UPDATE bike SET address= ? WHERE id = ?";
    private String address;
    private int bikeId;

    public BikeUpdateAddressSpecification(int bikeId, String address) {
        this.address = address;
        this.bikeId = bikeId;
    }

    @Override
    public PreparedStatement specify(ProxyConnection connection) throws SQLException {
        Logger.debug("update bike address");
        PreparedStatement statement = connection.prepareStatement(UPDATE);
        statement.setString(1, address);
        statement.setInt(2, bikeId);
        return statement;
    }
}
