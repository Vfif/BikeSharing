package com.epam.project.command.impl;

import com.epam.project.command.ActionCommand;
import com.epam.project.controller.Router;
import com.epam.project.entity.Trip;
import com.epam.project.exception.CommandException;
import com.epam.project.exception.ServiceException;
import com.epam.project.resource.ConfigurationManager;
import com.epam.project.service.HistoryService;
import com.epam.project.type.PageChangeType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.epam.project.command.ParameterName.LOGIN;
import static com.epam.project.command.ParameterName.TRIPS;

public class HistoryCommand implements ActionCommand {
    private static Logger Logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Logger.debug("Get trip list command");
        Router router = new Router();
        router.setPage(ConfigurationManager.getProperty("path.page.tripList"));
        router.setWay(PageChangeType.FORWARD);
        String login = (String) request.getSession().getAttribute(LOGIN);
        try {
            List<Trip> trips = HistoryService.getInstance().findTrips(login);
            request.getSession().setAttribute(TRIPS, trips);
        } catch (
                ServiceException e) {
            Logger.error(e);
            throw new CommandException(e);
        }

        return router;
    }
}
