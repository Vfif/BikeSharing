package com.epam.project.service;

import com.epam.project.entity.Client;
import com.epam.project.exception.RepositoryException;
import com.epam.project.exception.ServiceException;
import com.epam.project.repository.impl.ClientRepository;
import com.epam.project.repository.specification.impl.ClientSelectUserSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;
import java.util.stream.Collectors;

public class GetUserListService {
    private static Logger Logger = LogManager.getLogger();

    public static Map<String, Boolean> get() throws ServiceException {
        try {
            return ClientRepository.getInstance()
                    .query(new ClientSelectUserSpecification())
                    .stream()
                    .collect(Collectors.toMap(Client::getLogin, Client::isStatus));
        } catch (RepositoryException e) {
            Logger.error(e);
            throw new ServiceException(e);
        }
    }
}
