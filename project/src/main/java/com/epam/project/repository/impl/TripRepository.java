package com.epam.project.repository.impl;

import com.epam.project.connection.ConnectionPool;
import com.epam.project.connection.ProxyConnection;
import com.epam.project.entity.Trip;
import com.epam.project.exception.ConnectionPoolException;
import com.epam.project.exception.RepositoryException;
import com.epam.project.repository.AbstractRepository;
import com.epam.project.repository.specification.QuerySpecification;
import org.apache.logging.log4j.LogManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.epam.project.type.ParameterName.*;

public class TripRepository implements AbstractRepository<Trip> {
    private static final TripRepository instance = new TripRepository();
    private static final String INSERT = "INSERT INTO trip (money, user_id, bike_id, time) VALUES (?,?,?,?)";
    private static org.apache.logging.log4j.Logger Logger = LogManager.getLogger();
    private static final String TRIP = "trip.";

    private TripRepository() {
    }

    public static TripRepository getInstance() {
        return instance;
    }

    @Override
    public void save(Trip entity) throws RepositoryException {
        try (ProxyConnection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT)) {
            statement.setDouble(1, entity.getMoney());
            statement.setInt(2, entity.getUserId());
            statement.setInt(3, entity.getBikeId());
            statement.setLong(4, entity.getTime().getTime());
            statement.executeUpdate();
        } catch (SQLException | ConnectionPoolException e) {
            throw new RepositoryException(e);
        }
    }

    @Override
    public void remove(Trip entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Trip> query(QuerySpecification specification) throws RepositoryException {
        List<Trip> tripList = new ArrayList<>();
        try (ProxyConnection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = specification.specify(connection)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Trip trip = new Trip();
                trip.setMark(resultSet.getInt(MARK));
                trip.setUserId(resultSet.getInt(USER_ID));
                trip.setBikeId(resultSet.getInt(TRIP + BIKE_ID));
                trip.setMoney(resultSet.getDouble(MONEY));
                trip.setTime(new Date(resultSet.getLong(TRIP + TIME)));
                trip.setId(resultSet.getInt(TRIP + ID));
                trip.setBikeName(resultSet.getString(BIKE + "." + NAME));
                tripList.add(trip);
            }
        } catch (SQLException | ConnectionPoolException e) {
            Logger.error("SQL exception (request or table failed): ", e);
            throw new RepositoryException(e);
        }
        return tripList;
    }
}