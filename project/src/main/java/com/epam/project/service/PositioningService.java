package com.epam.project.service;

import com.epam.project.entity.Bike;
import com.epam.project.exception.RepositoryException;
import com.epam.project.exception.ServiceException;
import com.epam.project.repository.impl.BikeRepository;
import com.epam.project.repository.specification.bike.BikeSelectFreeSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Comparator;
import java.util.List;

public class PositioningService {
    private static Logger Logger = LogManager.getLogger();
    private static PositioningService instance;

    private PositioningService() {
    }

    public static PositioningService getInstance() {
        if (instance == null) {
            instance = new PositioningService();
        }
        return instance;
    }

    public Bike findTheNearestBike(int location) throws ServiceException {
        try {
            List<Bike> bikes = BikeRepository.getInstance().query(new BikeSelectFreeSpecification());
            if (bikes.isEmpty()) {
                return null;
            } else if (bikes.size() == 1) {
                return bikes.get(0);
            } else {
                return bikes.stream()//find the nearest if many found
                        .min(Comparator.comparingInt(o -> Math.abs(location - o.getLocation())))
                        .get();
            }
        } catch (RepositoryException e) {
            Logger.error(e);
            throw new ServiceException(e);
        }
    }
}
