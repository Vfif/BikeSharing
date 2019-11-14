package com.epam.project.service;

import com.epam.project.entity.Client;
import com.epam.project.exception.RepositoryException;
import com.epam.project.exception.ServiceException;
import com.epam.project.repository.impl.UserRepository;
import com.epam.project.repository.specification.Specification;
import com.epam.project.repository.specification.impl.SelectByLoginAndPasswordClientsSpecification;
import com.epam.project.util.EncryptPassword;

import java.util.List;

public class LoginService {
    public static Client getClientFromDatabase(String login, String password) throws ServiceException {
        try {
            String encryptPassword = EncryptPassword.encrypt(password);
            Specification specification = new SelectByLoginAndPasswordClientsSpecification(login, encryptPassword);
            List<Client> clients = UserRepository.getInstance().query(specification);
            return clients.isEmpty()? null : clients.get(0);
        } catch (RepositoryException e) {
            e.printStackTrace();
            throw new ServiceException(e);
        }
    }
}
