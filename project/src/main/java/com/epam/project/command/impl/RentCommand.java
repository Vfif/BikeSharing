package com.epam.project.command.impl;

import com.epam.project.command.ActionCommand;
import com.epam.project.controller.Router;
import com.epam.project.exception.CommandException;
import com.epam.project.exception.ServiceException;
import com.epam.project.resource.ConfigurationManager;
import com.epam.project.service.RentService;
import com.epam.project.type.PageChangeType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

import static com.epam.project.type.ParameterName.*;

public class RentCommand implements ActionCommand {
    private static Logger Logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Logger.debug("Rent command");
        Router router = new Router();
        int bikeId = (int) request.getSession().getAttribute(BIKE_ID);
        String login = (String) request.getSession().getAttribute(LOGIN);

        try {
            if (RentService.getInstance().isBikeFreeById(bikeId) &&
                    RentService.getInstance().isClientNotBan(login)) {
                router.setPage(ConfigurationManager.getProperty("path.page.rent"));
                long time = new Date().getTime();
                RentService.getInstance().updateUserByBikeId(login, bikeId);
                RentService.getInstance().updateBikeRentTime(bikeId, time);
                request.getSession().setAttribute(TIME, time);
            } else {
                router.setPage(ConfigurationManager.getProperty("path.page.end"));
                request.setAttribute(INVALID, true);
            }
        } catch (ServiceException e) {
            Logger.error(e);
            throw new CommandException(e);
        }
        router.setWay(PageChangeType.REDIRECT);
        return router;
    }
}
