package com.epam.project.service;

import com.epam.project.exception.RepositoryException;
import com.epam.project.exception.ServiceException;
import com.epam.project.repository.impl.ClientRepository;
import com.epam.project.repository.specification.client.ClientUpdateStatusSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SaveUserStatusService {
    private static Logger Logger = LogManager.getLogger();
    private static SaveUserStatusService instance;

    private SaveUserStatusService() {
    }

    public static SaveUserStatusService getInstance() {
        if (instance == null) {
            instance = new SaveUserStatusService();
        }
        return instance;
    }

    public void updateUserStatusByLogin(String login, boolean status) throws ServiceException {
        try {
            ClientRepository.getInstance()
                    .update(new ClientUpdateStatusSpecification(login, status));
        } catch (RepositoryException e) {
            Logger.error(e);
            throw new ServiceException(e);
        }
    }
}
