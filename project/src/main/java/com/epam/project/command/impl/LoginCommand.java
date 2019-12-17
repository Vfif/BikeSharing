package com.epam.project.command.impl;

import com.epam.project.command.ActionCommand;
import com.epam.project.controller.Router;
import com.epam.project.entity.Bike;
import com.epam.project.entity.Client;
import com.epam.project.exception.CommandException;
import com.epam.project.exception.ServiceException;
import com.epam.project.resource.ConfigurationManager;
import com.epam.project.service.LoginService;
import com.epam.project.validation.LoginValidation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

import static com.epam.project.type.ParameterName.*;
import static com.epam.project.type.ClientType.ADMIN;
import static com.epam.project.type.ClientType.USER;
import static com.epam.project.type.PageChangeType.FORWARD;

public class LoginCommand implements ActionCommand {
    private static Logger Logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Logger.debug("Login command");
        Router router = new Router();
        router.setWay(FORWARD);

        String login = request.getParameter(LOGIN);
        String password = request.getParameter(PASSWORD);
        try {
            if (LoginValidation.getInstance().isXssAttack(login)
                    || LoginValidation.getInstance().isXssAttack(password)) {
                throw new CommandException();
            }
            Client client = LoginService.getInstance().findClient(login, password);
            if (client != null) {
                if (client.getRole() == ADMIN) {
                    router.setPage(ConfigurationManager.getProperty("path.page.admin"));
                } else if (client.getRole() == USER) {
                    if (client.getBikeId() != -1) {
                        router.setPage(ConfigurationManager.getProperty("path.page.rent"));
                        request.getSession().setAttribute(BIKE_ID, client.getBikeId());
                        Bike bike = LoginService.getInstance().findBikeById(client.getBikeId());
                        request.getSession().setAttribute(COST, bike.getCost());
                        request.getSession().setAttribute(TIME, bike.getRentTime());
                    } else {
                        if (client.isStatus()) {
                            router.setPage(ConfigurationManager.getProperty("path.page.block"));
                        } else {
                            router.setPage(ConfigurationManager.getProperty("path.page.load"));
                        }
                    }
                }
                request.getSession().setAttribute(LOGIN, client.getLogin());
                request.getSession().setAttribute(ROLE, client.getRole().toString().toLowerCase());
                request.getSession().setAttribute(CASH, client.getCash());

            } else {
                request.setAttribute("errorLogOrPass", true);
                router.setPage(ConfigurationManager.getProperty("path.page.login"));
            }
        } catch (ServiceException e) {
            Logger.error(e);
            throw new CommandException(e);
        }
        return router;
    }
}
