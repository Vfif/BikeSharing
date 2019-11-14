package com.epam.project.repository;

import com.epam.project.connection.ConnectionPool;
import com.epam.project.connection.ProxyConnection;
import com.epam.project.entity.Entity;
import com.epam.project.exception.ConnectionPoolException;
import com.epam.project.exception.RepositoryException;
import com.epam.project.repository.specification.Specification;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public interface AbstractRepository<T extends Entity> {
    void save(T entity) throws RepositoryException;
    void remove(T entity);
    List<T> query(Specification specification) throws RepositoryException;

    default void update(Specification specification) throws RepositoryException{
        try (ProxyConnection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = specification.specify(connection)) {
            statement.executeUpdate();
        } catch (SQLException | ConnectionPoolException e) {
            throw new RepositoryException(e);
        }
    }
}



