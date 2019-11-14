package com.epam.project.service;

import com.epam.project.exception.RepositoryException;
import com.epam.project.exception.ServiceException;
import com.epam.project.repository.impl.ClientRepository;
import com.epam.project.repository.specification.impl.ClientUpdateStatusSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;

public class SaveStatusService {
    private static Logger Logger = LogManager.getLogger();

    public static void updateUserStatus(Map<String, Boolean> users) throws ServiceException {
        try {
            for (Map.Entry<String, Boolean> user : users.entrySet()) {
                ClientRepository.getInstance()
                        .update(new ClientUpdateStatusSpecification(user.getKey(), user.getValue()));
            }
        } catch (RepositoryException e) {
            Logger.error(e);
            throw new ServiceException(e);
        }
    }
}
