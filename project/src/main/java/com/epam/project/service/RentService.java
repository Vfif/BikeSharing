package com.epam.project.service;

import com.epam.project.entity.Bike;
import com.epam.project.entity.Client;
import com.epam.project.exception.RepositoryException;
import com.epam.project.exception.ServiceException;
import com.epam.project.repository.impl.BikeRepository;
import com.epam.project.repository.impl.ClientRepository;
import com.epam.project.repository.specification.bike.BikeSelectByIdNotRentSpecification;
import com.epam.project.repository.specification.bike.BikeUpdateTimeSpecification;
import com.epam.project.repository.specification.client.ClientSelectByLoginSpecification;
import com.epam.project.repository.specification.client.ClientUpdateBikeIdSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class RentService {
    private static Logger Logger = LogManager.getLogger();
    private static RentService instance;

    private RentService() {
    }

    public static RentService getInstance() {
        if (instance == null) {
            instance = new RentService();
        }
        return instance;
    }

    public boolean isBikeFreeById(int id) throws ServiceException {
        try {
            List<Bike> bikes = BikeRepository.getInstance().query(new BikeSelectByIdNotRentSpecification(id));
            return !bikes.isEmpty();
        } catch (RepositoryException e) {
            Logger.error(e);
            throw new ServiceException(e);
        }
    }

    public void updateUserByBikeId(String login, int bikeId) throws ServiceException {
        try {
            BikeRepository.getInstance().update(new ClientUpdateBikeIdSpecification(bikeId, login));
        } catch (RepositoryException e) {
            Logger.error(e);
            throw new ServiceException(e);
        }
    }

    public void updateBikeRentTime(int bikeId, long rentTime) throws ServiceException {
        try {
            BikeRepository.getInstance().update(new BikeUpdateTimeSpecification(bikeId, rentTime));
        } catch (RepositoryException e) {
            Logger.error(e);
            throw new ServiceException(e);
        }
    }

    public boolean isClientNotBan(String login) throws ServiceException {
        try {
            Client client = ClientRepository.getInstance()
                    .query(new ClientSelectByLoginSpecification(login))
                    .get(0);
            return !client.isStatus();
        } catch (RepositoryException e) {
            Logger.error(e);
            throw new ServiceException(e);
        }
    }
}
