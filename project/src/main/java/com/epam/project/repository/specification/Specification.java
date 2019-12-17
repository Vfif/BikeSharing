package com.epam.project.repository.specification;

import com.epam.project.connection.ProxyConnection;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * The interface Specification for Repository.
 * @see         UpdateSpecification
 * @see         QuerySpecification
 * @author      Mariya Gurskaya
 * @since       1.0
 */
public interface Specification {
    /**
     * Specify prepared statement.
     *
     * @param connection the proxyConnection
     * @return the prepared statement which consist of database information
     * according to SQL request
     * @throws SQLException the sql exception
     */
    PreparedStatement specify(ProxyConnection connection) throws SQLException;
}

