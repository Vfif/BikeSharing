package com.epam.project.repository.impl;

import com.epam.project.connection.ConnectionPool;
import com.epam.project.connection.ProxyConnection;
import com.epam.project.entity.Card;
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

import static com.epam.project.command.ParameterName.CASH;
import static com.epam.project.command.ParameterName.NUMBER;

public class CardRepository implements AbstractRepository<Card> {
    private static Logger Logger = LogManager.getLogger();
    private static final CardRepository instance = new CardRepository();

    private CardRepository() {
    }

    public static CardRepository getInstance() {
        return instance;
    }

    @Override
    public void save(Card entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void remove(Card entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Card> query(QuerySpecification specification) throws RepositoryException {
        List<Card> cards = new ArrayList<>();
        try (ProxyConnection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = specification.specify(connection)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Card card = new Card();
                card.setNumber(resultSet.getString(NUMBER));
                card.setCash(resultSet.getDouble(CASH));
                cards.add(card);
            }
        } catch (SQLException | ConnectionPoolException e) {
            Logger.error("SQL exception (request or table failed): ", e);
            throw new RepositoryException(e);
        }
        return cards;
    }
}
