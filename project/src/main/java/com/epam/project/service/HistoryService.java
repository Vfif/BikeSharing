package com.epam.project.service;

import com.epam.project.entity.Trip;
import com.epam.project.exception.RepositoryException;
import com.epam.project.exception.ServiceException;
import com.epam.project.repository.impl.TripRepository;
import com.epam.project.repository.specification.trip.TripSelectAllByUserIdSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class HistoryService {
    private static Logger Logger = LogManager.getLogger();
    private static HistoryService instance;

    private HistoryService() {
    }

    public static HistoryService getInstance() {
        if (instance == null) {
            instance = new HistoryService();
        }
        return instance;
    }

    public List<Trip> findTrips(String login) throws ServiceException {
        try {
            return TripRepository.getInstance()
                    .query(new TripSelectAllByUserIdSpecification(login));
        } catch (RepositoryException e) {
            Logger.error(e);
            throw new ServiceException(e);
        }
    }
}
