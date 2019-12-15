package com.epam.project.service;

import com.epam.project.entity.Client;
import com.epam.project.entity.Trip;
import com.epam.project.exception.RepositoryException;
import com.epam.project.exception.ServiceException;
import com.epam.project.repository.impl.ClientRepository;
import com.epam.project.repository.impl.TripRepository;
import com.epam.project.repository.specification.client.ClientSelectByLoginSpecification;
import com.epam.project.repository.specification.client.ClientUpdateCashSpecification;
import com.epam.project.repository.specification.trip.TripSelectByBikeIdAndTimeSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Date;

public class PayService {
    private static Logger Logger = LogManager.getLogger();
    private static PayService instance;

    private PayService(){}

    public static PayService getInstance(){
        if(instance == null){
            instance = new PayService();
        }
        return instance;
    }

    public double payForRental(String login, long currentTime, long rentTime, double cash, double cost) throws ServiceException {
        double payment = Math.ceil(((currentTime - rentTime)* cost )/(60 * 60 * 1000));
        double newCash = cash - payment;
        try {
            ClientRepository.getInstance().update(new ClientUpdateCashSpecification(login, newCash));
        } catch (RepositoryException e) {
            Logger.error(e);
            throw new ServiceException(e);
        }
        return payment;
    }

    public void registerTrip(String login, int bikeId, double money, Date time) throws ServiceException {
        try {
            Client client = ClientRepository.getInstance()
                    .query(new ClientSelectByLoginSpecification(login))
                    .get(0);
            TripRepository.getInstance().save(new Trip(client.getId(), bikeId, money, time));
        } catch (RepositoryException e) {
            Logger.error(e);
            throw new ServiceException(e);
        }
    }

    public int getTripId(int bikeId, Date date) throws ServiceException {
        try {
            long time = date.getTime();
            return TripRepository.getInstance()
                    .query(new TripSelectByBikeIdAndTimeSpecification(bikeId, time))
                    .get(0)
                    .getId();
        } catch (RepositoryException e) {
            Logger.error(e);
            throw new ServiceException(e);
        }
    }
}

