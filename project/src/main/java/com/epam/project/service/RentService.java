package com.epam.project.service;

import com.epam.project.entity.Bike;
import com.epam.project.exception.RepositoryException;
import com.epam.project.exception.ServiceException;
import com.epam.project.repository.impl.BikeRepository;
import com.epam.project.repository.specification.impl.BikeSelectByIdNotRentSpecification;
import com.epam.project.repository.specification.impl.BikeUpdateRentTimeSpecification;
import com.epam.project.repository.specification.impl.ClientUpdateBikeIdSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class RentService {
    private static Logger Logger = LogManager.getLogger();

    public static boolean isBikeFreeById(int id) throws ServiceException {
        try {
            List<Bike> bikes = BikeRepository.getInstance().query(new BikeSelectByIdNotRentSpecification(id));
            return !bikes.isEmpty();
        } catch (RepositoryException e) {
            Logger.error(e);
            throw new ServiceException(e);
        }
    }

    public static void updateUserByBikeId(String login, int bikeId) throws ServiceException {
        try {
            BikeRepository.getInstance().update(new ClientUpdateBikeIdSpecification(bikeId, login));
        } catch (RepositoryException e) {
            Logger.error(e);
            throw new ServiceException(e);
        }
    }

    public static void updateBikeRentTime(int bikeId, long rentTime) throws ServiceException {
        try {
            BikeRepository.getInstance().update(new BikeUpdateRentTimeSpecification(bikeId, rentTime));
        } catch (RepositoryException e) {
            Logger.error(e);
            throw new ServiceException(e);
        }
    }
}
