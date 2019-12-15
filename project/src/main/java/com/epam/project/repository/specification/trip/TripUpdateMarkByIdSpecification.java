package com.epam.project.repository.specification.trip;

import com.epam.project.connection.ProxyConnection;
import com.epam.project.repository.specification.UpdateSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TripUpdateMarkByIdSpecification implements UpdateSpecification {
    private static final String UPDATE = "UPDATE trip SET mark = ? WHERE id = ?";
    private static Logger Logger = LogManager.getLogger();
    private int id;
    private int mark;

    public TripUpdateMarkByIdSpecification(int id, int mark) {
        this.id = id;
        this.mark = mark;
    }

    @Override
    public PreparedStatement specify(ProxyConnection connection) throws SQLException {
        Logger.debug("update trip mark by trip id");
        PreparedStatement statement = connection.prepareStatement(UPDATE);
        statement.setInt(1, mark);
        statement.setInt(2, id);
        return statement;
    }
}
