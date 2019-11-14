package com.epam.project.command.impl;

import com.epam.project.command.ActionCommand;
import com.epam.project.controller.PageInfo;
import com.epam.project.resource.ConfigurationManager;
import com.epam.project.type.PageChangeType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class LogoutCommand implements ActionCommand {
    private static Logger Logger = LogManager.getLogger();

    @Override
    public PageInfo execute(HttpServletRequest request) {
        Logger.debug("Logout command");
        PageInfo pageInfo = new PageInfo();
        pageInfo.setPage(ConfigurationManager.getProperty("path.page.index"));
        pageInfo.setWay(PageChangeType.REDIRECT);
        request.getSession().invalidate();
        return pageInfo;
    }
}