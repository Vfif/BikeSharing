package com.epam.project.service;

import com.epam.project.entity.Client;
import com.epam.project.exception.RepositoryException;
import com.epam.project.repository.impl.ClientRepository;
import com.epam.project.repository.specification.client.ClientSelectByLoginSpecification;
import com.epam.project.validation.RegistrationValidation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.epam.project.type.ParameterName.*;

public class RegistrationService {
    private static Logger Logger = LogManager.getLogger();
    private static RegistrationService instance;

    private RegistrationService() {
    }

    public static RegistrationService getInstance() {
        if (instance == null) {
            instance = new RegistrationService();
        }
        return instance;
    }

    public Map<String, Boolean> checkRegistrationForm(String login, String password, String email) {
        boolean incorrectLogin = !RegistrationValidation.getInstance().isCorrectLogin(login)
                | RegistrationService.getInstance().isClientWithLoginExist(login);
        boolean incorrectPassword = !RegistrationValidation.getInstance().isCorrectPassword(password);
        boolean incorrectEmail = !RegistrationValidation.getInstance().isCorrectEmail(email);
        Map<String, Boolean> map = new HashMap<>();
        map.put(INCORRECT_LOGIN, incorrectLogin);
        map.put(INCORRECT_PASSWORD, incorrectPassword);
        map.put(INCORRECT_EMAIL, incorrectEmail);
        return map;
    }

    public boolean isClientWithLoginExist(String login) {
        try {
            List<Client> clients = ClientRepository.getInstance().query(new ClientSelectByLoginSpecification(login));
            return !clients.isEmpty();
        } catch (RepositoryException e) {
            Logger.error(e);
            return false;
        }
    }
}
