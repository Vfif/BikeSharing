package com.epam.project.command.impl;

import com.epam.project.command.ActionCommand;
import com.epam.project.controller.Router;
import com.epam.project.entity.Client;
import com.epam.project.exception.CommandException;
import com.epam.project.exception.RepositoryException;
import com.epam.project.repository.impl.ClientRepository;
import com.epam.project.resource.ConfigurationManager;
import com.epam.project.service.RegistrationService;
import com.epam.project.util.EncryptPassword;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

import static com.epam.project.command.ParameterName.*;
import static com.epam.project.type.PageChangeType.FORWARD;
import static com.epam.project.type.PageChangeType.REDIRECT;

public class RegistrationCommand implements ActionCommand {
    private static Logger Logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Logger.debug("Registration command");
        Router router = new Router();

        String login = request.getParameter(LOGIN);
        String password = request.getParameter(PASSWORD);
        String email = request.getParameter(EMAIL);

        Map<String, Boolean> resultOfError = RegistrationService.getInstance().checkRegistrationForm(login, password, email);
        boolean invalidResult = resultOfError.values().stream().filter(o -> o.equals(true)).findAny().orElse(false);

        if (!invalidResult) {
            Client client = new Client();
            client.setLogin(login);
            client.setPassword(EncryptPassword.encrypt(password));
            client.setEmail(email);
            try {
                ClientRepository.getInstance().save(client);
            } catch (RepositoryException e) {
                throw new CommandException(e);
            }
            router.setPage(ConfigurationManager.getProperty("path.page.login"));
            router.setWay(REDIRECT);
        } else {
            resultOfError.forEach(request::setAttribute);
            router.setPage(ConfigurationManager.getProperty("path.page.registration"));
            router.setWay(FORWARD);
        }
        return router;
    }
}
