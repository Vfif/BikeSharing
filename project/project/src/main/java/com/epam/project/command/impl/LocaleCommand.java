package com.epam.project.command.impl;

import com.epam.project.command.ActionCommand;
import com.epam.project.controller.PageInfo;
import com.epam.project.exception.CommandException;
import com.epam.project.resource.ConfigurationManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

import static com.epam.project.type.PageChangeType.FORWARD;

public class LocaleCommand implements ActionCommand {
    private static Logger Logger = LogManager.getLogger();
    private static final String LOCALE = "locale";
    private static final String RUS = "ru_RU";
    private static final String ENG = "en_US";

    @Override
    public PageInfo execute(HttpServletRequest request) throws CommandException {
        String locale = (String) request.getSession().getAttribute(LOCALE);
        if (locale.equals(RUS)) {
            request.getSession().setAttribute(LOCALE, ENG);
        } else if (locale.equals(ENG)) {
            request.getSession().setAttribute(LOCALE, RUS);
        } else {
            Logger.error("Unsupported locale" + locale);
            throw new CommandException("Unsupported locale" + locale);
        }
        PageInfo pageInfo = new PageInfo();
        pageInfo.setPage(ConfigurationManager.getProperty("path.page.first"));
        pageInfo.setWay(FORWARD);
        return pageInfo;
    }
}