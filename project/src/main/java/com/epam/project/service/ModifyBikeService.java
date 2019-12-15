package com.epam.project.service;

import com.epam.project.entity.Bike;
import com.epam.project.exception.RepositoryException;
import com.epam.project.exception.ServiceException;
import com.epam.project.repository.impl.BikeRepository;
import com.epam.project.repository.specification.bike.BikeUpdateAddressSpecification;
import com.epam.project.repository.specification.bike.BikeUpdateCostSpecification;
import com.epam.project.repository.specification.bike.BikeUpdateDescriptionSpecification;
import com.epam.project.repository.specification.bike.BikeUpdateNameSpecification;
import com.epam.project.validation.AddBikeValidation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

import static com.epam.project.command.ParameterName.*;

public class ModifyBikeService {
    private static Logger Logger = LogManager.getLogger();
    private static ModifyBikeService instance;

    private ModifyBikeService() {
    }

    public static ModifyBikeService getInstance() {
        if (instance == null) {
            instance = new ModifyBikeService();
        }
        return instance;
    }

    public Map<String, Boolean> checkModifyBikeForm(String field, String value) throws ServiceException {
        Map<String, Boolean> map = new HashMap<>();
        switch (field) {
            case NAME:
                boolean incorrectName = !AddBikeValidation.getInstance().isCorrectName(value);
                map.put(INCORRECT_NAME, incorrectName);
                break;
            case COST:
                boolean incorrectCost = !AddBikeValidation.getInstance().isCorrectCost(value);
                map.put(INCORRECT_COST, incorrectCost);
                break;
            case ADDRESS:
                boolean incorrectAddress = !AddBikeValidation.getInstance().isCorrectAddress(value);
                map.put(INCORRECT_ADDRESS, incorrectAddress);
                break;
            case DESCRIPTION:
                boolean incorrectDescription = !AddBikeValidation.getInstance().isCorrectDescription(value);
                map.put(INCORRECT_DESCRIPTION, incorrectDescription);
                break;
            default:
                throw new ServiceException("No such parameter in bike modify form");
        }
        return map;
    }

    public void modifyBike(Bike bike, String field, String value) throws ServiceException {
        try {
            switch (field) {
                case NAME:
                    BikeRepository.getInstance().update(new BikeUpdateNameSpecification(bike.getId(), value));
                    bike.setName(value);
                    break;
                case COST:
                    int cost = Integer.parseInt(value);
                    BikeRepository.getInstance().update(new BikeUpdateCostSpecification(bike.getId(), cost));
                    bike.setCost(cost);
                    break;
                case ADDRESS:
                    BikeRepository.getInstance().update(new BikeUpdateAddressSpecification(bike.getId(), value));
                    bike.setAddress(value);
                    break;
                case DESCRIPTION:
                    BikeRepository.getInstance().update(new BikeUpdateDescriptionSpecification(bike.getId(), value));
                    bike.setDescription(value);
                    break;
                default:
                    throw new ServiceException("No such parameter in bike modify form");
            }
        } catch (RepositoryException ex) {
            Logger.error(ex);
            throw new ServiceException(ex);

        }
    }
}
