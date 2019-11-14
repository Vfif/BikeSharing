package com.epam.project.command.impl;

import com.epam.project.command.ActionCommand;
import com.epam.project.controller.PageInfo;
import com.epam.project.entity.Client;
import com.epam.project.exception.CommandException;
import com.epam.project.exception.ServiceException;
import com.epam.project.resource.ConfigurationManager;
import com.epam.project.service.LoginService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

import static com.epam.project.type.PageChangeType.FORWARD;

public class LoginCommand implements ActionCommand {
    private static Logger Logger = LogManager.getLogger();
    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";

    @Override
    public PageInfo execute(HttpServletRequest request) throws CommandException {
        Logger.debug("Login command");
        PageInfo pageInfo = new PageInfo();
        pageInfo.setWay(FORWARD);

        String login = request.getParameter(LOGIN);
        String password = request.getParameter(PASSWORD);
        try {
            Client client = LoginService.getClientFromDatabase(login, password);
            if (client != null) {
                pageInfo.setPage(ConfigurationManager.getProperty("path.page.main"));
                request.setAttribute("user", client.getLogin());
            } else {
                request.setAttribute("errorLogOrPass",true);
                pageInfo.setPage(ConfigurationManager.getProperty("path.page.login"));
            }
        } catch (ServiceException e) {
            Logger.error(e);
            throw new CommandException(e);
        }
        return pageInfo;
    }
}
