package com.epam.project.service;

import com.epam.project.exception.RepositoryException;
import com.epam.project.exception.ServiceException;
import com.epam.project.repository.impl.BikeRepository;
import com.epam.project.repository.specification.bike.BikeUpdateDeleteSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DeleteBikeService {
    private static Logger Logger = LogManager.getLogger();
    private static DeleteBikeService instance;

    private DeleteBikeService(){}

    public static DeleteBikeService getInstance(){
        if(instance == null){
            instance = new DeleteBikeService();
        }
        return instance;
    }

    public void deleteBikeById(int bikeId) throws ServiceException {
        try {
            BikeRepository.getInstance()
                    .update(new BikeUpdateDeleteSpecification(bikeId));
        } catch (RepositoryException e) {
            Logger.error(e);
            throw new ServiceException(e);
        }
    }
}

