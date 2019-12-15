package com.epam.project.command.impl;

import com.epam.project.command.ActionCommand;
import com.epam.project.controller.Router;
import com.epam.project.entity.Bike;
import com.epam.project.exception.CommandException;
import com.epam.project.exception.RepositoryException;
import com.epam.project.repository.impl.BikeRepository;
import com.epam.project.resource.ConfigurationManager;
import com.epam.project.service.AddBikeService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

import static com.epam.project.command.ParameterName.*;
import static com.epam.project.type.PageChangeType.FORWARD;
import static com.epam.project.type.PageChangeType.REDIRECT;

public class AddBikeCommand implements ActionCommand {

    private static Logger Logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Logger.debug("Add bike command");
        Router router = new Router();
        String applicationDir = request.getServletContext().getRealPath("");
        String uploadFileDir = applicationDir + File.separator + UPLOAD_DIR + File.separator;
        File fileSaveDir = new File(uploadFileDir);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdirs();
        }
        String imagePath = null;
        Part part;
        try {
            part = request.getParts().stream()
                    .filter(p -> p.getSubmittedFileName() != null && !p.getSubmittedFileName().isEmpty())
                    .findFirst().orElse(null);
        } catch (IOException | ServletException e) {
            Logger.error(e);
            throw new CommandException(e);
        }

        String name = request.getParameter(NAME);
        String cost = request.getParameter(COST);
        String description = request.getParameter(DESCRIPTION);
        String address = request.getParameter(ADDRESS);

        Map<String, Boolean> resultOfError = AddBikeService.getInstance()
                .checkAddBikeForm(name, cost, description, address, part);
        boolean invalidResult = resultOfError.values().stream().filter(o -> o.equals(true)).findAny().orElse(false);

        if (!invalidResult) {
            try {
                String path = part.getSubmittedFileName();
                imagePath = UUID.randomUUID() + path.substring(path.indexOf("."));
                part.write(uploadFileDir + imagePath);
            } catch (IOException e) {
                Logger.error(e);
            }

            Bike bike = new Bike();
            bike.setName(name);
            bike.setCost(Double.parseDouble(cost));
            bike.setDescription(description);
            bike.setAddress(address);
            bike.setImage(imagePath);

            try {
                BikeRepository.getInstance().save(bike);
            } catch (RepositoryException e) {
                throw new CommandException(e);
            }
            router.setWay(REDIRECT);
            router.setPage(ConfigurationManager.getProperty("path.page.admin"));
        } else {
            router.setWay(FORWARD);
            resultOfError.forEach(request::setAttribute);
            router.setPage(ConfigurationManager.getProperty("path.page.addBike"));
        }
        return router;
    }
}