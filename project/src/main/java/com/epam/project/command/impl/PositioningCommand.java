package com.epam.project.command.impl;

import com.epam.project.command.ActionCommand;
import com.epam.project.controller.PageInfo;
import com.epam.project.entity.Bike;
import com.epam.project.exception.CommandException;
import com.epam.project.exception.ServiceException;
import com.epam.project.resource.ConfigurationManager;
import com.epam.project.service.PositioningService;
import com.epam.project.type.PageChangeType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Random;

public class PositioningCommand implements ActionCommand {
    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String COST = "cost";
    private static final String DESCRIPTION = "description";
    private static final String IMAGE = "image";
    private static final String ADDRESS = "address";
    private static final String UPLOAD_DIR = "bike_image";
    private static Logger Logger = LogManager.getLogger();

    @Override
    public PageInfo execute(HttpServletRequest request) throws CommandException {
        Logger.debug("Positioning command");
        PageInfo pageInfo = new PageInfo();
        pageInfo.setWay(PageChangeType.FORWARD);
        int location = new Random().nextInt(1000);
        Bike bike;
        try {
            bike = PositioningService.getTheNearestBike(location);
        } catch (ServiceException e) {
            Logger.error(e);
            throw new CommandException(e);
        }
        if (bike != null) {
            pageInfo.setPage(ConfigurationManager.getProperty("path.page.user"));
            request.setAttribute(ID, bike.getId());
            request.setAttribute(NAME, bike.getName());
            request.setAttribute(COST, bike.getCost());
            request.setAttribute(DESCRIPTION, bike.getDescription());
            request.setAttribute(ADDRESS, bike.getAddress());
            String imageDirectoryPath = request.getServletContext().getRealPath("")
                    + File.separator + UPLOAD_DIR + File.separator;
            request.setAttribute(IMAGE, imageDirectoryPath + bike.getImage());

        }else{
            pageInfo.setPage(ConfigurationManager.getProperty("path.page.noFreeBikes"));
        }
        return pageInfo;
    }
}
