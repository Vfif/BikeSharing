package com.epam.project.command.impl;

import com.epam.project.command.ActionCommand;
import com.epam.project.controller.Router;
import com.epam.project.exception.CommandException;
import com.epam.project.resource.ConfigurationManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

import static com.epam.project.type.ParameterName.*;
import static com.epam.project.type.PageChangeType.FORWARD;

public class LocaleCommand implements ActionCommand {
    private static Logger Logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        String locale = (String) request.getSession().getAttribute(LOCALE);
        if (locale.equals(RUS)) {
            request.getSession().setAttribute(LOCALE, ENG);
        } else if (locale.equals(ENG)) {
            request.getSession().setAttribute(LOCALE, RUS);
        } else {
            Logger.error("Unsupported locale" + locale);
            throw new CommandException("Unsupported locale" + locale);
        }
        Router router = new Router();
        router.setPage(ConfigurationManager.getProperty("path.page.first"));
        router.setWay(FORWARD);
        return router;
    }
}