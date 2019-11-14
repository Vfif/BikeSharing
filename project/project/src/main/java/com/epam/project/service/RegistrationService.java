package com.epam.project.service;

import com.epam.project.entity.Client;
import com.epam.project.exception.RepositoryException;
import com.epam.project.repository.impl.UserRepository;
import com.epam.project.repository.specification.Specification;
import com.epam.project.repository.specification.impl.SelectByLoginClientsSpecification;
import com.epam.project.validation.RegistrationValidation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RegistrationService {
    private static Logger Logger = LogManager.getLogger();

    public static Map<String, Boolean> checkRegistrationForm(String login, String password, String email) {
        boolean incorrectLogin = RegistrationService.isClientWithLoginExist(login);
        boolean incorrectPassword = !RegistrationValidation.isCorrectPassword(password);
        boolean incorrectEmail = !RegistrationValidation.isCorrectEmail(email);
        Map<String, Boolean> map = new HashMap<>();
        map.put("incorrectLogin", incorrectLogin);
        map.put("incorrectPassword", incorrectPassword);
        map.put("incorrectEmail", incorrectEmail);
        return map;
    }

    public static boolean isClientWithLoginExist(String login) {
        try {
            Specification specification = new SelectByLoginClientsSpecification(login);
            List<Client> clients = UserRepository.getInstance().query(specification);
            return !clients.isEmpty();
        } catch (RepositoryException e) {
            Logger.error(e);
            return false;
        }
    }
}
