package com.epam.project.repository;

import com.epam.project.connection.ConnectionPool;
import com.epam.project.connection.ProxyConnection;
import com.epam.project.entity.Entity;
import com.epam.project.exception.ConnectionPoolException;
import com.epam.project.exception.RepositoryException;
import com.epam.project.repository.impl.BikeRepository;
import com.epam.project.repository.impl.CardRepository;
import com.epam.project.repository.impl.ClientRepository;
import com.epam.project.repository.impl.TripRepository;
import com.epam.project.repository.specification.QuerySpecification;
import com.epam.project.repository.specification.Specification;
import com.epam.project.repository.specification.UpdateSpecification;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * The interface Abstract repository.
 * Designed for connection with database.
 * Used to get or change information in database.
 *
 * @param <T> the type parameter of Repository
 * @see         Specification
 * @see         UpdateSpecification
 * @see         QuerySpecification
 * @see         BikeRepository
 * @see         ClientRepository
 * @see         CardRepository
 * @see         TripRepository
 * @author      Mariya Gurskaya
 * @since       1.0
 */
public interface AbstractRepository<T extends Entity> {
    /**
     * Save entity in database.
     *
     * @param entity the entity which will be added in database
     * @throws RepositoryException the repository exception
     * if cannot execute sql request
     */
    void save(T entity) throws RepositoryException;

    /**
     * Remove entity from database.
     *
     * @param entity the entity
     */
    void remove(T entity);

    /**
     * Query list of entities according to specification.
     *
     * @param specification the specification for SELECT SQL request
     * @return the list of entities according to sql request in specification
     * @throws RepositoryException the repository exception
     */
    List<T> query(QuerySpecification specification) throws RepositoryException;

    /**
     * Update data in database.
     *
     * @param specification the specification for UPDATE SQL request
     * @throws RepositoryException the repository exception
     */
    default void update(UpdateSpecification specification) throws RepositoryException{
        try (ProxyConnection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = specification.specify(connection)) {
            statement.executeUpdate();
        } catch (SQLException | ConnectionPoolException e) {
            throw new RepositoryException(e);
        }
    }
}



