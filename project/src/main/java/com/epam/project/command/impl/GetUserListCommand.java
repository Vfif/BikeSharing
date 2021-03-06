package com.epam.project.command.impl;

import com.epam.project.command.ActionCommand;
import com.epam.project.controller.Router;
import com.epam.project.entity.Client;
import com.epam.project.exception.CommandException;
import com.epam.project.exception.ServiceException;
import com.epam.project.resource.ConfigurationManager;
import com.epam.project.service.GetUserListService;
import com.epam.project.type.PageChangeType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.epam.project.type.ParameterName.USERS;

public class GetUserListCommand implements ActionCommand {
    private static Logger Logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Logger.debug("Get user list command");
        Router router = new Router();
        router.setPage(ConfigurationManager.getProperty("path.page.banUser"));
        router.setWay(PageChangeType.FORWARD);
        try {
            List<Client> users = GetUserListService.getInstance().findUsers();
            request.getSession().setAttribute(USERS, users);
        } catch (
                ServiceException e) {
            Logger.error(e);
            throw new CommandException(e);
        }

        return router;
    }
}