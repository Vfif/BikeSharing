package com.epam.project.repository.impl;

import com.epam.project.connection.ConnectionPool;
import com.epam.project.connection.ProxyConnection;
import com.epam.project.entity.Bike;
import com.epam.project.exception.ConnectionPoolException;
import com.epam.project.exception.RepositoryException;
import com.epam.project.repository.AbstractRepository;
import com.epam.project.repository.specification.QuerySpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.epam.project.command.ParameterName.*;

public class BikeRepository implements AbstractRepository<Bike> {
    private static final BikeRepository instance = new BikeRepository();
    private static final String INSERT =
            "INSERT INTO bike (name, cost, description, image, address, location) VALUES (?,?,?,?,?,?)";
    private static Logger Logger = LogManager.getLogger();

    private BikeRepository() {
    }

    public static BikeRepository getInstance() {
        return instance;
    }

    @Override
    public void save(Bike entity) throws RepositoryException {
        try (ProxyConnection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT)) {
            statement.setString(1, entity.getName());
            statement.setDouble(2, entity.getCost());
            statement.setString(3, entity.getDescription());
            statement.setString(4, entity.getImage());
            statement.setString(5, entity.getAddress());
            statement.setInt(6, new Random().nextInt(1000));
            statement.executeUpdate();
        } catch (SQLException | ConnectionPoolException e) {
            throw new RepositoryException(e);
        }
    }

    @Override
    public void remove(Bike entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Bike> query(QuerySpecification specification) throws RepositoryException {
        List<Bike> bikes = new ArrayList<>();
        try (ProxyConnection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = specification.specify(connection)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Bike bike = new Bike();
                bike.setId(resultSet.getInt(ID));
                bike.setName(resultSet.getString(NAME));
                bike.setCost(resultSet.getDouble(COST));
                bike.setDescription(resultSet.getString(DESCRIPTION));
                bike.setImage(resultSet.getString(IMAGE));
                bike.setRentTime(resultSet.getLong(TIME));
                bike.setAddress(resultSet.getString(ADDRESS));
                bike.setLocation(resultSet.getInt(LOCATION));
                bike.setStatus(resultSet.getBoolean(STATUS));
                bikes.add(bike);
            }
        } catch (SQLException | ConnectionPoolException e) {
            Logger.error("SQL exception (request or table failed): ", e);
            throw new RepositoryException(e);
        }
        return bikes;
    }
}













