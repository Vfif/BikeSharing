package com.epam.project.command.impl;

import com.epam.project.command.ActionCommand;
import com.epam.project.controller.Router;
import com.epam.project.resource.ConfigurationManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

import static com.epam.project.command.ParameterName.PAGE;
import static com.epam.project.type.PageChangeType.FORWARD;

public class GoToCommand implements ActionCommand {
    private static Logger Logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) {
        String page = request.getParameter(PAGE);
        String removeAttribute = request.getParameter("delete");
        if (removeAttribute != null) request.getSession().removeAttribute(removeAttribute);
        Logger.debug("command: go to " + page);
        Router router = new Router();
        router.setPage(ConfigurationManager.getProperty("path.page." + page));
        router.setWay(FORWARD);
        return router;
    }
}
