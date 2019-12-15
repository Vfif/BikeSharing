package com.epam.project.command.impl;

import com.epam.project.command.ActionCommand;
import com.epam.project.controller.Router;
import com.epam.project.exception.CommandException;
import com.epam.project.exception.ServiceException;
import com.epam.project.resource.ConfigurationManager;
import com.epam.project.service.ReplenishmentService;
import com.epam.project.type.PageChangeType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

import static com.epam.project.command.ParameterName.*;

public class ReplenishmentCommand implements ActionCommand {
    private static Logger Logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Logger.debug("Replenishment command");
        Router router = new Router();
        String number = request.getParameter(NUMBER);
        String money = request.getParameter(CASH);//input cash
        try {
            if (ReplenishmentService.getInstance().isCorrectCardData(number, money)) {
                router.setPage(ConfigurationManager.getProperty("path.page.rent"));
                String login = (String) request.getSession().getAttribute(LOGIN);
                double cash = (double)request.getSession().getAttribute(CASH);//user cash
                double inputCash = Double.parseDouble(money);
                ReplenishmentService.getInstance().replenishment(login, number, cash, inputCash);
                request.getSession().setAttribute(CASH, inputCash + cash);
                router.setWay(PageChangeType.REDIRECT);
            } else {
                router.setWay(PageChangeType.FORWARD);
                request.setAttribute(INVALID_CARD, true);
                router.setPage(ConfigurationManager.getProperty("path.page.replenishment"));
            }
        } catch (ServiceException e) {
            Logger.error(e);
            throw new CommandException(e);
        }
        return router;
    }
}
