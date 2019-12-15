package com.epam.project.service;

import com.epam.project.exception.RepositoryException;
import com.epam.project.exception.ServiceException;
import com.epam.project.repository.impl.CardRepository;
import com.epam.project.repository.impl.ClientRepository;
import com.epam.project.repository.specification.card.CardSelectByNumberCashSpecification;
import com.epam.project.repository.specification.card.CardUpdateCashSpecification;
import com.epam.project.repository.specification.client.ClientUpdateCashSpecification;
import com.epam.project.validation.ReplenishmentValidation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ReplenishmentService {
    private static Logger Logger = LogManager.getLogger();
    private static ReplenishmentService instance;

    private ReplenishmentService() {
    }

    public static ReplenishmentService getInstance() {
        if (instance == null) {
            instance = new ReplenishmentService();
        }
        return instance;
    }

    public boolean isCorrectCardData(String number, String amount) throws ServiceException {
        try {
            if (ReplenishmentValidation.getInstance().isCorrectCard(number) &&
                    ReplenishmentValidation.getInstance().isCorrectAmount(amount)) {
                double cash = Double.parseDouble(amount);
                return !CardRepository.getInstance()
                        .query(new CardSelectByNumberCashSpecification(number, cash))
                        .isEmpty();
            } else {
                return false;
            }
        } catch (RepositoryException e) {
            Logger.error(e);
            throw new ServiceException(e);
        }
    }

    public void replenishment(String login, String number, double cash, double inputCash) throws ServiceException {
        try {
            ClientRepository.getInstance()
                    .transaction(
                            new CardUpdateCashSpecification(number, inputCash),
                            new ClientUpdateCashSpecification(login, cash + inputCash));
        } catch (RepositoryException e) {
            Logger.error(e);
            throw new ServiceException(e);
        }
    }
}
