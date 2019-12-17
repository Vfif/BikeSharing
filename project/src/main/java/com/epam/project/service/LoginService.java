package com.epam.project.service;

import com.epam.project.entity.Bike;
import com.epam.project.entity.Client;
import com.epam.project.exception.RepositoryException;
import com.epam.project.exception.ServiceException;
import com.epam.project.repository.impl.BikeRepository;
import com.epam.project.repository.impl.ClientRepository;
import com.epam.project.repository.specification.QuerySpecification;
import com.epam.project.repository.specification.bike.BikeSelectByIdSpecification;
import com.epam.project.repository.specification.client.ClientSelectByLoginAndPasswordSpecification;
import com.epam.project.util.EncryptPassword;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class LoginService {
    private static Logger Logger = LogManager.getLogger();
    private static LoginService instance;

    private LoginService() {
    }

    public static LoginService getInstance() {
        if (instance == null) {
            instance = new LoginService();
        }
        return instance;
    }

    public Client findClient(String login, String password) throws ServiceException {
        try {
            String encryptPassword = EncryptPassword.encrypt(password);
            QuerySpecification specification = new ClientSelectByLoginAndPasswordSpecification(login, encryptPassword);
            List<Client> clients = ClientRepository.getInstance().query(specification);
            return clients.isEmpty() ? null : clients.get(0);
        } catch (RepositoryException e) {
            Logger.error(e);
            throw new ServiceException(e);
        }
    }

    public Bike findBikeById(int id) throws ServiceException {
        try {
            return BikeRepository.getInstance().query(new BikeSelectByIdSpecification(id)).get(0);
        } catch (RepositoryException e) {
            Logger.error(e);
            throw new ServiceException(e);
        }
    }
}
