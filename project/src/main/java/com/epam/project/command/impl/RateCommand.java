package com.epam.project.command.impl;

import com.epam.project.command.ActionCommand;
import com.epam.project.controller.Router;
import com.epam.project.exception.CommandException;
import com.epam.project.exception.ServiceException;
import com.epam.project.resource.ConfigurationManager;
import com.epam.project.service.RateService;
import com.epam.project.type.PageChangeType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

import static com.epam.project.type.ParameterName.*;

public class RateCommand implements ActionCommand {
    private static Logger Logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Logger.debug("Trip command");
        String rate = request.getParameter(RATE);
        int tripId = (int) request.getSession().getAttribute(ID);
        try {
            RateService.getInstance().rate(tripId, rate);
        } catch (ServiceException e) {
            Logger.error(e);
            throw new CommandException(e);
        }
        request.getSession().invalidate();
        Router router = new Router();
        router.setPage(ConfigurationManager.getProperty("path.page.index"));
        router.setWay(PageChangeType.REDIRECT);
        return router;
    }
}
