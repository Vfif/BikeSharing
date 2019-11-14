package com.epam.project.repository.specification.impl;

import com.epam.project.connection.ProxyConnection;
import com.epam.project.repository.specification.Specification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BikeUpdateRentTimeSpecification implements Specification {
    private static final String UPDATE = "UPDATE bike SET rentTime = ? WHERE id = ?";
    private static Logger Logger = LogManager.getLogger();
    private int id;
    private long rentTime;

    public BikeUpdateRentTimeSpecification(int id, long rentTime) {
        this.id = id;
        this.rentTime = rentTime;
    }

    @Override
    public PreparedStatement specify(ProxyConnection connection) throws SQLException {
        Logger.debug("update client by login");
        PreparedStatement statement = connection.prepareStatement(UPDATE);
        statement.setLong(1, rentTime);
        statement.setInt(2, id);
        return statement;
    }
}
