package com.epam.project.command.impl;

import com.epam.project.command.ActionCommand;
import com.epam.project.controller.PageInfo;
import com.epam.project.exception.CommandException;
import com.epam.project.exception.ServiceException;
import com.epam.project.resource.ConfigurationManager;
import com.epam.project.service.SaveStatusService;
import com.epam.project.type.PageChangeType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class SaveStatusCommand implements ActionCommand {
    private static Logger Logger = LogManager.getLogger();
    private static String USERS = "users";

    @Override
    public PageInfo execute(HttpServletRequest request) throws CommandException {

        Logger.debug("Save user status command");
        PageInfo pageInfo = new PageInfo();
        pageInfo.setPage(ConfigurationManager.getProperty("path.page.admin"));
        pageInfo.setWay(PageChangeType.FORWARD);
        Map<String, Boolean> users = (Map<String, Boolean>) request.getAttribute(USERS);
        try {
            SaveStatusService.updateUserStatus(users);
        } catch (ServiceException e) {
            Logger.error(e);
            throw new CommandException(e);
        }
        return pageInfo;
    }
}