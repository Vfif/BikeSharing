package com.epam.project.repository.specification.trip;

import com.epam.project.connection.ProxyConnection;
import com.epam.project.repository.specification.QuerySpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TripSelectAllByUserIdSpecification implements QuerySpecification {
    private static final String SELECT =
            "SELECT trip.id, mark, user_id, trip.bike_id, money, trip.time, bike.name FROM trip " +
                    "JOIN client ON client.id = trip.user_id " +
                    "JOIN bike ON trip.bike_id = bike.id " +
                    "WHERE login = ?";
    private static Logger Logger = LogManager.getLogger();
    private String login;

    public TripSelectAllByUserIdSpecification(String login) {
        this.login = login;
    }

    @Override
    public PreparedStatement specify(ProxyConnection connection) throws SQLException {
        Logger.debug("find all trips by userId");
        PreparedStatement statement = connection.prepareStatement(SELECT);
        statement.setString(1, login);
        return statement;
    }
}
