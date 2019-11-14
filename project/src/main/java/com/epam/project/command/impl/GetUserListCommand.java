package com.epam.project.command.impl;

import com.epam.project.command.ActionCommand;
import com.epam.project.controller.PageInfo;
import com.epam.project.exception.CommandException;
import com.epam.project.exception.ServiceException;
import com.epam.project.resource.ConfigurationManager;
import com.epam.project.service.GetUserListService;
import com.epam.project.type.PageChangeType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class GetUserListCommand implements ActionCommand {
    private static Logger Logger = LogManager.getLogger();

    @Override
    public PageInfo execute(HttpServletRequest request) throws CommandException {
        Logger.debug("Get user list command");
        PageInfo pageInfo = new PageInfo();
        pageInfo.setPage(ConfigurationManager.getProperty("path.page.banUser"));
        pageInfo.setWay(PageChangeType.FORWARD);
        try {
            Map<String, Boolean> users = GetUserListService.get();
            request.setAttribute("users", users);
        } catch (
                ServiceException e) {
            Logger.error(e);
            throw new CommandException(e);
        }

        return pageInfo;
    }
}