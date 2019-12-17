package com.epam.project.command.impl;

import com.epam.project.command.ActionCommand;
import com.epam.project.controller.Router;
import com.epam.project.entity.Bike;
import com.epam.project.exception.CommandException;
import com.epam.project.resource.ConfigurationManager;
import com.epam.project.type.PageChangeType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.epam.project.type.ParameterName.*;

@SuppressWarnings("unchecked")
public class ChangeBikeCommand implements ActionCommand {
    private static Logger Logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {

        Logger.debug("go to change bike command");
        Router router = new Router();
        router.setPage(ConfigurationManager.getProperty("path.page.modifyBike"));
        router.setWay(PageChangeType.FORWARD);
        int bikeId = Integer.parseInt(request.getParameter(BIKE_ID));
        List<Bike> users = (List<Bike>) request.getSession().getAttribute(BIKES);
        Bike bike = users.stream()
                .filter(obj -> bikeId == obj.getId())
                .findFirst()
                .orElse(null);
        request.getSession().setAttribute(BIKE, bike);
        return router;
    }
}