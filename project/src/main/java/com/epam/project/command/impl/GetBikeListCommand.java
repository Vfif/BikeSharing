package com.epam.project.command.impl;

import com.epam.project.command.ActionCommand;
import com.epam.project.controller.Router;
import com.epam.project.entity.Bike;
import com.epam.project.exception.CommandException;
import com.epam.project.exception.ServiceException;
import com.epam.project.resource.ConfigurationManager;
import com.epam.project.service.GetBikeListService;
import com.epam.project.type.PageChangeType;
import org.apache.logging.log4j.LogManager;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.epam.project.type.ParameterName.BIKES;
import static com.epam.project.type.ParameterName.PAGE;

public class GetBikeListCommand implements ActionCommand {
    private static org.apache.logging.log4j.Logger Logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Logger.debug("Get bike list command");
        Router router = new Router();
        String page = request.getParameter(PAGE);
        router.setPage(ConfigurationManager.getProperty("path.page." + page));
        router.setWay(PageChangeType.FORWARD);
        try {
            List<Bike> bikes = GetBikeListService.getInstance().findBikes();
            request.getSession().setAttribute(BIKES, bikes);
        } catch (
                ServiceException e) {
            Logger.error(e);
            throw new CommandException(e);
        }

        return router;
    }
}