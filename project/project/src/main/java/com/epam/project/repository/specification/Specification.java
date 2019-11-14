package com.epam.project.repository.specification;

import com.epam.project.connection.ProxyConnection;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface Specification { //есть ли смысл делать generic?
    PreparedStatement specify(ProxyConnection connection) throws SQLException;
}

