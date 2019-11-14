package com.epam.project.command.impl;

import com.epam.project.command.ActionCommand;
import com.epam.project.controller.PageInfo;
import com.epam.project.entity.Client;
import com.epam.project.exception.CommandException;
import com.epam.project.exception.RepositoryException;
import com.epam.project.repository.impl.UserRepository;
import com.epam.project.resource.ConfigurationManager;
import com.epam.project.service.RegistrationService;
import com.epam.project.util.EncryptPassword;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

import static com.epam.project.type.PageChangeType.FORWARD;
import static com.epam.project.type.PageChangeType.REDIRECT;

public class RegistrationCommand implements ActionCommand {
    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";
    private static final String EMAIL = "email";
    private static Logger Logger = LogManager.getLogger();

    @Override
    public PageInfo execute(HttpServletRequest request) throws CommandException {
        Logger.debug("Registration command");
        PageInfo pageInfo = new PageInfo();

        String login = request.getParameter(LOGIN);
        String password = request.getParameter(PASSWORD);
        String email = request.getParameter(EMAIL);

        Map<String, Boolean> resultOfError = RegistrationService.checkRegistrationForm(login, password, email);

        resultOfError.forEach(request::setAttribute);
        boolean invalidResult = resultOfError.values().stream().filter(o -> o.equals(true)).findAny().orElse(false);

        if (!invalidResult) {
            Client client = new Client();
            client.setLogin(login);
            client.setPassword(EncryptPassword.encrypt(password));
            client.setEmail(email);
            try {
                UserRepository.getInstance().save(client);
            } catch (RepositoryException e) {
                throw new CommandException(e);
            }
            pageInfo.setPage(ConfigurationManager.getProperty("path.page.login"));
            pageInfo.setWay(REDIRECT);
        } else {
            pageInfo.setPage(ConfigurationManager.getProperty("path.page.registration"));
            pageInfo.setWay(FORWARD);
        }
        return pageInfo;
    }
}
