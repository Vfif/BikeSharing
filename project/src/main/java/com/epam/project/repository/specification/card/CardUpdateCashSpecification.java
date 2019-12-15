package com.epam.project.repository.specification.card;

import com.epam.project.connection.ProxyConnection;
import com.epam.project.repository.specification.UpdateSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CardUpdateCashSpecification implements UpdateSpecification {
    private static final String UPDATE = "UPDATE card SET cash = cash - ? WHERE number = ?";
    private static Logger Logger = LogManager.getLogger();
    private String number;
    private double cash;

    public CardUpdateCashSpecification(String number, double cash) {
        this.number = number;
        this.cash = cash;
    }

    @Override
    public PreparedStatement specify(ProxyConnection connection) throws SQLException {
        Logger.debug("update client");
        PreparedStatement statement = connection.prepareStatement(UPDATE);
        statement.setDouble(1, cash);
        statement.setString(2, number);
        return statement;
    }
}
