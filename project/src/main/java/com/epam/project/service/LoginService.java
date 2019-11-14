package com.epam.project.service;

import com.epam.project.entity.Client;
import com.epam.project.exception.RepositoryException;
import com.epam.project.exception.ServiceException;
import com.epam.project.repository.impl.ClientRepository;
import com.epam.project.repository.specification.Specification;
import com.epam.project.repository.specification.impl.ClientSelectByLoginAndPasswordSpecification;
import com.epam.project.util.EncryptPassword;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class LoginService {
    private static Logger Logger = LogManager.getLogger();

    public static Client getClientFromDatabase(String login, String password) throws ServiceException {
        try {
            String encryptPassword = EncryptPassword.encrypt(password);
            Specification specification = new ClientSelectByLoginAndPasswordSpecification(login, encryptPassword);
            List<Client> clients = ClientRepository.getInstance().query(specification);
            return clients.isEmpty()? null : clients.get(0);
        } catch (RepositoryException e) {
            Logger.error(e);
            throw new ServiceException(e);
        }
    }
}
