package com.epam.project.repository.specification.card;

import com.epam.project.connection.ProxyConnection;
import com.epam.project.repository.specification.QuerySpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CardSelectByNumberCashSpecification implements QuerySpecification {
    private static final String SELECT = "SELECT number, cash  FROM card WHERE number=? AND cash >= ?";
    private static Logger Logger = LogManager.getLogger();
    private String number;
    private double cash;

    public CardSelectByNumberCashSpecification(String number, double cash) {
        this.number = number;
        this.cash = cash;
    }

    @Override
    public PreparedStatement specify(ProxyConnection connection) throws SQLException {
        Logger.debug("find client by login and password");
        PreparedStatement statement = connection.prepareStatement(SELECT);
        statement.setString(1, number);
        statement.setDouble(2, cash);
        return statement;
    }
}
