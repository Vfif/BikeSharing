package com.epam.project.repository.impl;

import com.epam.project.connection.ConnectionPool;
import com.epam.project.connection.ProxyConnection;
import com.epam.project.entity.Bike;
import com.epam.project.exception.ConnectionPoolException;
import com.epam.project.exception.RepositoryException;
import com.epam.project.repository.AbstractRepository;
import com.epam.project.repository.specification.Specification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BikeRepository implements AbstractRepository<Bike> {
    private static final BikeRepository instance = new BikeRepository();
    private static final String INSERT = "INSERT INTO bike (name, cost, description, image, address, location) VALUES (?,?,?,?,?,?)";
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
    public List<Bike> query(Specification specification) throws RepositoryException {
        List<Bike> bikes = new ArrayList<>();
        try (ProxyConnection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = specification.specify(connection)) {
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Bike bike = new Bike();
                bike.setId(resultSet.getInt("id"));
                bike.setName(resultSet.getString("name"));
                bike.setCost(resultSet.getDouble("cost"));
                bike.setDescription(resultSet.getString("description"));
                bike.setImage(resultSet.getString("image"));
                bike.setRentTime(resultSet.getLong("rentTime"));
                bike.setAddress(resultSet.getString("address"));
                bike.setLocation(resultSet.getInt("location"));
                bikes.add(bike);
            }
        } catch (SQLException | ConnectionPoolException e) {
            Logger.error("SQL exception (request or table failed): ", e);
            throw new RepositoryException(e);
        }
        return bikes;
    }
}













