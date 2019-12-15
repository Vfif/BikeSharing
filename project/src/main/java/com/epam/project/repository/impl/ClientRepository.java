package com.epam.project.repository.impl;

import com.epam.project.connection.ConnectionPool;
import com.epam.project.connection.ProxyConnection;
import com.epam.project.entity.Client;
import com.epam.project.exception.ConnectionPoolException;
import com.epam.project.exception.RepositoryException;
import com.epam.project.repository.AbstractRepository;
import com.epam.project.repository.specification.QuerySpecification;
import com.epam.project.repository.specification.UpdateSpecification;
import com.epam.project.type.ClientType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.epam.project.command.ParameterName.*;

public class ClientRepository implements AbstractRepository<Client> {
    private static final ClientRepository instance = new ClientRepository();
    private static final String INSERT = "INSERT INTO client (login, password, email, registrationDate) VALUES (?,?,?,?)";
    private static Logger Logger = LogManager.getLogger();

    private ClientRepository() {
    }

    public static ClientRepository getInstance() {
        return instance;
    }

    @Override
    public void save(Client entity) throws RepositoryException {
        try (ProxyConnection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT)) {
            statement.setString(1, entity.getLogin());
            statement.setString(2, entity.getPassword());
            statement.setString(3, entity.getEmail());
            statement.setLong(4, new Date().getTime());
            statement.executeUpdate();
        } catch (SQLException | ConnectionPoolException e) {
            throw new RepositoryException(e);
        }
    }

    @Override
    public void remove(Client entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Client> query(QuerySpecification specification) throws RepositoryException {
        List<Client> clients = new ArrayList<>();
        try (ProxyConnection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = specification.specify(connection)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Client client = new Client();
                client.setId(resultSet.getInt(ID));
                client.setRole(ClientType.valueOf(resultSet.getString("role.name").toUpperCase()));
                client.setLogin(resultSet.getString(LOGIN));
                client.setPassword(resultSet.getString(PASSWORD));
                client.setEmail(resultSet.getString(EMAIL));
                client.setCash(resultSet.getDouble(CASH));
                client.setStatus(resultSet.getInt(STATUS) == 1);//if 1 - user is banned
                client.setBikeId(resultSet.getInt(BIKE_ID));
                clients.add(client);
            }
        } catch (SQLException | ConnectionPoolException e) {
            Logger.error("SQL exception (request or table failed): ", e);
            throw new RepositoryException(e);
        }
        return clients;
    }

    public void transaction(UpdateSpecification... specifications) throws RepositoryException {
        try (ProxyConnection connection = ConnectionPool.getInstance().getConnection()) {
            try {
                connection.setAutoCommit(false);
                for (UpdateSpecification specification : specifications) {
                    try (PreparedStatement statement = specification.specify(connection)) {
                        statement.executeUpdate();
                    }
                }
                connection.commit();
            } catch (SQLException ex) {
                connection.rollback();
                Logger.error(ex);
                throw new RepositoryException(ex);
            }
        } catch (SQLException | ConnectionPoolException e) {
            Logger.error(e);
            throw new RepositoryException(e);
        }
    }
}






