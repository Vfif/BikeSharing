package com.epam.project.service;

import com.epam.project.exception.RepositoryException;
import com.epam.project.exception.ServiceException;
import com.epam.project.repository.impl.TripRepository;
import com.epam.project.repository.specification.trip.TripUpdateMarkByIdSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RateService {
    private static Logger Logger = LogManager.getLogger();
    private static RateService instance;

    private RateService(){}

    public static RateService getInstance(){
        if(instance == null){
            instance = new RateService();
        }
        return instance;
    }

    public void rate(int id, String rate) throws ServiceException {
        if(rate!= null) {
            int mark = Integer.parseInt(rate);
            try {
            TripRepository.getInstance().update(new TripUpdateMarkByIdSpecification(id, mark));
            } catch (RepositoryException e) {
                Logger.error(e);
                throw new ServiceException(e);
            }
        }
    }
}
