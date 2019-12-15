package com.epam.project.service;

import com.epam.project.entity.Bike;
import com.epam.project.exception.RepositoryException;
import com.epam.project.exception.ServiceException;
import com.epam.project.repository.impl.BikeRepository;
import com.epam.project.repository.specification.bike.BikeSelectAllSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class GetBikeListService {
    private static Logger Logger = LogManager.getLogger();
    private static GetBikeListService instance;

    private GetBikeListService(){}

    public static GetBikeListService getInstance(){
        if(instance == null){
            instance = new GetBikeListService();
        }
        return instance;
    }

    public List<Bike> findBikes() throws ServiceException {
        try {
            return BikeRepository.getInstance()
                    .query(new BikeSelectAllSpecification());
        } catch (RepositoryException e) {
            Logger.error(e);
            throw new ServiceException(e);
        }
    }
}
