package com.epam.project.command.impl;

import com.epam.project.command.ActionCommand;
import com.epam.project.controller.Router;
import com.epam.project.entity.Bike;
import com.epam.project.exception.CommandException;
import com.epam.project.exception.ServiceException;
import com.epam.project.resource.ConfigurationManager;
import com.epam.project.service.PositioningService;
import com.epam.project.type.PageChangeType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Random;

import static com.epam.project.command.ParameterName.*;

public class PositioningCommand implements ActionCommand {
    private static Logger Logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Logger.debug("Positioning command");
        Router router = new Router();
        router.setWay(PageChangeType.FORWARD);
        int location = new Random().nextInt(1000);
        Bike bike;
        try {
            bike = PositioningService.getInstance().findTheNearestBike(location);
        } catch (ServiceException e) {
            Logger.error(e);
            throw new CommandException(e);
        }
        if (bike != null) {
            router.setPage(ConfigurationManager.getProperty("path.page.user"));
            request.getSession().setAttribute(BIKE_ID, bike.getId());
            request.setAttribute(NAME, bike.getName());
            request.getSession().setAttribute(COST, bike.getCost());
            request.setAttribute(DESCRIPTION, bike.getDescription());
            request.setAttribute(ADDRESS, bike.getAddress());
            request.setAttribute(IMAGE, UPLOAD_DIR + File.separator + bike.getImage());

        }else{
            router.setPage(ConfigurationManager.getProperty("path.page.noFreeBikes"));
        }
        return router;
    }
}
