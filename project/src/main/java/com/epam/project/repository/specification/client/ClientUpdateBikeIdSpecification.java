package com.epam.project.repository.specification.client;

import com.epam.project.connection.ProxyConnection;
import com.epam.project.repository.specification.UpdateSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ClientUpdateBikeIdSpecification implements UpdateSpecification {
    private static final String UPDATE = "UPDATE client SET bike_id = ? WHERE login = ?";
    private static Logger Logger = LogManager.getLogger();
    private int bikeId;
    private String login;

    public ClientUpdateBikeIdSpecification(int bikeId, String login) {
        this.bikeId = bikeId;
        this.login = login;
    }

    @Override
    public PreparedStatement specify(ProxyConnection connection) throws SQLException {
        Logger.debug("update client by login");
        PreparedStatement statement = connection.prepareStatement(UPDATE);
        statement.setInt(1, bikeId);
        statement.setString(2, login);
        return statement;
    }
}
