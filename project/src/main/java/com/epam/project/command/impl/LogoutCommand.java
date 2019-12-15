package com.epam.project.command.impl;

import com.epam.project.command.ActionCommand;
import com.epam.project.controller.Router;
import com.epam.project.resource.ConfigurationManager;
import com.epam.project.type.PageChangeType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class LogoutCommand implements ActionCommand {
    private static Logger Logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) {
        Logger.debug("Logout command");
        Router router = new Router();
        request.getSession().invalidate();
        router.setPage(ConfigurationManager.getProperty("path.page.index"));
        router.setWay(PageChangeType.REDIRECT);
        return router;
    }
}