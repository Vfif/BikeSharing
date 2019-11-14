package com.epam.project.command.impl;

import com.epam.project.command.ActionCommand;
import com.epam.project.controller.PageInfo;
import com.epam.project.exception.CommandException;
import com.epam.project.exception.ServiceException;
import com.epam.project.resource.ConfigurationManager;
import com.epam.project.service.RentService;
import com.epam.project.type.PageChangeType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

public class RentCommand implements ActionCommand {
    private static Logger Logger = LogManager.getLogger();

    @Override
    public PageInfo execute(HttpServletRequest request) throws CommandException {
        Logger.debug("Rent command");
        PageInfo pageInfo = new PageInfo();
        int bikeId = (int) request.getSession().getAttribute("id");
        String login = (String) request.getSession().getAttribute("login");

        try {
            if (RentService.isBikeFreeById(bikeId)) {
                pageInfo.setPage(ConfigurationManager.getProperty("path.page.rent"));
                RentService.updateUserByBikeId(login, bikeId); //client -> bikeId
                long rentTime = new Date().getTime();
                RentService.updateBikeRentTime(bikeId, rentTime);//bike -> rentTime
                request.setAttribute("rentTime", rentTime);
            } else {
                pageInfo.setPage(ConfigurationManager.getProperty("path.page.end"));
                request.setAttribute("invalid", true);
            }
        } catch (ServiceException e) {
            Logger.error(e);
            throw new CommandException(e);
        }
        pageInfo.setWay(PageChangeType.FORWARD);
        return pageInfo;
    }
}
