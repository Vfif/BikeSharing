package com.epam.project.repository.specification.bike;

import com.epam.project.connection.ProxyConnection;
import com.epam.project.repository.specification.UpdateSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BikeUpdateTimeSpecification implements UpdateSpecification {
    private static Logger Logger = LogManager.getLogger();
    private static final String UPDATE = "UPDATE bike SET time = ? WHERE id = ?";
    private int id;
    private long time;

    public BikeUpdateTimeSpecification(int id, long rentTime) {
        this.id = id;
        this.time = rentTime;
    }

    @Override
    public PreparedStatement specify(ProxyConnection connection) throws SQLException {
        Logger.debug("update bike time by id");
        PreparedStatement statement = connection.prepareStatement(UPDATE);
        statement.setLong(1, time);
        statement.setInt(2, id);
        return statement;
    }
}
