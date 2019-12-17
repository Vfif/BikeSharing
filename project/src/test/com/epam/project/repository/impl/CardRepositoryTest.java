package com.epam.project.repository.impl;

import com.epam.project.entity.Card;
import com.epam.project.exception.RepositoryException;
import com.epam.project.repository.specification.card.CardSelectByNumberCashSpecification;
import com.epam.project.repository.specification.card.CardUpdateCashSpecification;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.*;

public class CardRepositoryTest {
    String number = "1111111111111111";
    double minCash = 0;

    @Test
    public void getInstance() {
        assertNotNull(CardRepository.getInstance());
    }

    @Test(expectedExceptions = UnsupportedOperationException.class)
    public void saveTest() {
        CardRepository.getInstance().save(new Card());
    }

    @Test(expectedExceptions = UnsupportedOperationException.class)
    public void removeTest() {
        CardRepository.getInstance().remove(new Card());
    }

    @Test
    public void queryTest() {
        try {
            List<Card> cardList = CardRepository.getInstance().query(new CardSelectByNumberCashSpecification(number, minCash));
            assertFalse(cardList.isEmpty());
        } catch (RepositoryException e) {
            fail();
        }
    }

    @Test(dependsOnMethods = "queryTest")
    public void updateTest() {
        double payment = 10;
        try {
            Card card = CardRepository.getInstance()
                    .query(new CardSelectByNumberCashSpecification(number, minCash))
                    .get(0);

            CardRepository.getInstance().update(new CardUpdateCashSpecification(number, payment));

            Card newCard = CardRepository.getInstance()
                    .query(new CardSelectByNumberCashSpecification(number, minCash))
                    .get(0);

            assertEquals(card.getCash() - payment, newCard.getCash());
        } catch (RepositoryException e) {
            fail();
        }
    }
}