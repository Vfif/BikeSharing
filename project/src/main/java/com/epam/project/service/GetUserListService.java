package com.epam.project.service;

import com.epam.project.entity.Client;
import com.epam.project.exception.RepositoryException;
import com.epam.project.exception.ServiceException;
import com.epam.project.repository.impl.ClientRepository;
import com.epam.project.repository.specification.client.ClientSelectUserSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class GetUserListService {
    private static Logger Logger = LogManager.getLogger();
    private static GetUserListService instance;

    private GetUserListService() {
    }

    public static GetUserListService getInstance() {
        if (instance == null) {
            instance = new GetUserListService();
        }
        return instance;
    }

    public List<Client> findUsers() throws ServiceException {
        try {
            return ClientRepository.getInstance()
                    .query(new ClientSelectUserSpecification());
        } catch (RepositoryException e) {
            Logger.error(e);
            throw new ServiceException(e);
        }
    }
}
