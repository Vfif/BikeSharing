package com.epam.project.service;

import com.epam.project.exception.RepositoryException;
import com.epam.project.exception.ServiceException;
import com.epam.project.repository.impl.ClientRepository;
import com.epam.project.repository.specification.impl.ClientUpdateCashSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PayService {
    private static Logger Logger = LogManager.getLogger();

    public static void payForRental(String login, long currentTime, long rentTime, double cash, double cost) throws ServiceException {
        double newCash = cash - (Math.ceil((currentTime - rentTime)/(60 * 60 * 1000)) * cost);
        try {
            ClientRepository.getInstance().update(new ClientUpdateCashSpecification(newCash, login));
        } catch (RepositoryException e) {
            Logger.error(e);
            throw new ServiceException(e);
        }
    }
}
