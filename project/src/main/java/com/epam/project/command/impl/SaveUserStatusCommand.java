package com.epam.project.command.impl;

import com.epam.project.command.ActionCommand;
import com.epam.project.controller.Router;
import com.epam.project.entity.Client;
import com.epam.project.exception.CommandException;
import com.epam.project.exception.ServiceException;
import com.epam.project.resource.ConfigurationManager;
import com.epam.project.service.SaveUserStatusService;
import com.epam.project.type.PageChangeType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.epam.project.command.ParameterName.*;

@SuppressWarnings("unchecked")
public class SaveUserStatusCommand implements ActionCommand {
    private static Logger Logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {

        Logger.debug("Save user status command");
        Router router = new Router();
        router.setPage(ConfigurationManager.getProperty("path.page.banUser"));
        router.setWay(PageChangeType.FORWARD);
        String login = request.getParameter(LOGIN);
        boolean status = Boolean.parseBoolean(request.getParameter(STATUS));
        List<Client> users = (List<Client>) request.getSession().getAttribute(USERS);
        users.stream().filter(o -> o.getLogin().equals(login))
                .findFirst()
                .ifPresent(user -> user.setStatus(!status));

        try {
            SaveUserStatusService.getInstance().updateUserStatusByLogin(login, !status);
        } catch (ServiceException e) {
            Logger.error(e);
            throw new CommandException(e);
        }
        return router;
    }
}