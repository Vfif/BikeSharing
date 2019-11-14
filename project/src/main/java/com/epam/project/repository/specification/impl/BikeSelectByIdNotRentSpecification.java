package com.epam.project.repository.specification.impl;

import com.epam.project.connection.ProxyConnection;
import com.epam.project.repository.specification.Specification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BikeSelectByIdNotRentSpecification implements Specification {
    private static final String SELECT_BY_ID ="SELECT id, name, cost, description, image, rentTime, address, location FROM bike WHERE id=? AND rentTime=0";
    private static Logger Logger = LogManager.getLogger();
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
