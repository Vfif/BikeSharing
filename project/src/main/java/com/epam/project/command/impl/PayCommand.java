package com.epam.project.command.impl;

import com.epam.project.command.ActionCommand;
import com.epam.project.controller.Router;
import com.epam.project.exception.CommandException;
import com.epam.project.exception.ServiceException;
import com.epam.project.resource.ConfigurationManager;
import com.epam.project.service.PayService;
import com.epam.project.service.RentService;
import com.epam.project.type.PageChangeType;
import com.epam.project.validation.PayValidation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

import static com.epam.project.type.ParameterName.*;

public class PayCommand implements ActionCommand {
    private static Logger Logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Logger.debug("Pay command");
        Router router = new Router();
        router.setWay(PageChangeType.FORWARD);
        long rentTime = (long) request.getSession().getAttribute(TIME);
        double cost = (double) request.getSession().getAttribute(COST);
        double cash = (double) request.getSession().getAttribute(CASH);
        long currentTime = new Date().getTime();

        if (PayValidation.getInstance().isEnoughMoney(currentTime, rentTime, cash, cost)) {
            router.setPage(ConfigurationManager.getProperty("path.page.end"));
            try {
                int bikeId = (int) request.getSession().getAttribute(BIKE_ID);
                String login = (String) request.getSession().getAttribute(LOGIN);
                Date time = new Date();
                double money = PayService.getInstance().payForRental(login, currentTime, rentTime, cash, cost);
                RentService.getInstance().updateUserByBikeId(login, -1);
                RentService.getInstance().updateBikeRentTime(bikeId, 0);
                PayService.getInstance().registerTrip(login, bikeId, money, time);
                int tripId =  PayService.getInstance().getTripId(bikeId, time);
                request.getSession().setAttribute(ID, tripId);
            } catch (ServiceException e) {
                Logger.error(e);
                throw new CommandException(e);
            }
        } else {
            request.setAttribute(INVALID, true);
            router.setPage(ConfigurationManager.getProperty("path.page.rent"));
        }
        return router;
    }
}