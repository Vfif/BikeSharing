package com.epam.project.repository.specification.bike;

import com.epam.project.connection.ProxyConnection;
import com.epam.project.repository.specification.UpdateSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BikeUpdateDeleteSpecification implements UpdateSpecification {
    private static Logger Logger = LogManager.getLogger();
    private static final String UPDATE = "UPDATE bike SET status = 1 WHERE id = ?";
    private int id;

    public BikeUpdateDeleteSpecification(int id) {
        this.id = id;
    }

    @Override
    public PreparedStatement specify(ProxyConnection connection) throws SQLException {
        Logger.debug("update bike status 1");
        PreparedStatement statement = connection.prepareStatement(UPDATE);
        statement.setInt(1, id);
        return statement;
    }
}
