package com.epam.project.repository.specification.trip;

import com.epam.project.connection.ProxyConnection;
import com.epam.project.repository.specification.QuerySpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TripSelectByBikeIdAndTimeSpecification implements QuerySpecification {
    private static final String SELECT =
            "SELECT trip.id, mark, user_id, trip.bike_id, money, trip.time, bike.name FROM trip " +
                    "JOIN bike ON trip.bike_id = bike.id " +
                    "WHERE trip.bike_id=? AND trip.time = ?";
    private static Logger Logger = LogManager.getLogger();
    private int bikeId;
    private long time;

    public TripSelectByBikeIdAndTimeSpecification(int bikeId, long time) {
        this.bikeId = bikeId;
        this.time = time;
    }

    @Override
    public PreparedStatement specify(ProxyConnection connection) throws SQLException {
        Logger.debug("find trip id");
        PreparedStatement statement = connection.prepareStatement(SELECT);
        statement.setInt(1, bikeId);
        statement.setLong(2, time);
        return statement;
    }
}
