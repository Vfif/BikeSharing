package com.epam.project.command.impl;

import com.epam.project.command.ActionCommand;
import com.epam.project.controller.PageInfo;
import com.epam.project.entity.Bike;
import com.epam.project.exception.CommandException;
import com.epam.project.exception.RepositoryException;
import com.epam.project.repository.impl.BikeRepository;
import com.epam.project.resource.ConfigurationManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

import static com.epam.project.type.PageChangeType.FORWARD;

public class AddBikeCommand implements ActionCommand {
    private static final String UPLOAD_DIR = "bike_image";
    private static final String NAME = "name";
    private static final String COST = "cost";
    private static final String DESCRIPTION = "description";
    private static final String ADDRESS = "address";
    private static Logger Logger = LogManager.getLogger();

    @Override
    public PageInfo execute(HttpServletRequest request) throws CommandException {
        Logger.debug("Add bike command");
        PageInfo pageInfo = new PageInfo();
        String applicationDir = request.getServletContext().getRealPath("");
        String uploadFileDir = applicationDir + File.separator + UPLOAD_DIR + File.separator;
        File fileSaveDir = new File(uploadFileDir);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdirs();
        }
        final String[] imagePath = {null};
        try {
            request.getParts().stream().filter(part -> part.getSubmittedFileName() != null)
                    .forEach(part -> {
                        try {
                            String path = part.getSubmittedFileName();
                            imagePath[0] = UUID.randomUUID() + path.substring(path.indexOf("."));
                            part.write(uploadFileDir + imagePath[0]);
                        } catch (IOException e) {
                            Logger.error(e);
                        }
                    });
        } catch (IOException | ServletException e) {
            Logger.error(e);
            throw new CommandException(e);
        }

        String name = request.getParameter(NAME);
        String cost = request.getParameter(COST);
        String description = request.getParameter(DESCRIPTION);
        String address = request.getParameter(ADDRESS);

        Bike bike = new Bike();
        bike.setName(name);
        bike.setCost(Double.parseDouble(cost));
        bike.setDescription(description);
        bike.setAddress(address);
        bike.setImage(imagePath[0]);

        try {
            BikeRepository.getInstance().save(bike);
        } catch (RepositoryException e) {
            throw new CommandException(e);
        }
        pageInfo.setPage(ConfigurationManager.getProperty("path.page.admin"));
        pageInfo.setWay(FORWARD);
        return pageInfo;
    }
}