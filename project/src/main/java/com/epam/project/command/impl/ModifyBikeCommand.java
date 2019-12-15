package com.epam.project.command.impl;

import com.epam.project.command.ActionCommand;
import com.epam.project.controller.Router;
import com.epam.project.entity.Bike;
import com.epam.project.exception.CommandException;
import com.epam.project.exception.ServiceException;
import com.epam.project.resource.ConfigurationManager;
import com.epam.project.service.ModifyBikeService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

import static com.epam.project.command.ParameterName.BIKE;
import static com.epam.project.command.ParameterName.BIKES;
import static com.epam.project.type.PageChangeType.FORWARD;

@SuppressWarnings("unchecked")
public class ModifyBikeCommand implements ActionCommand {
    private static final String FIELD = "field";
    private static final String VALUE = "value";
    private static Logger Logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Logger.debug("Modify command");
        Router router = new Router();
        router.setPage(ConfigurationManager.getProperty("path.page.modifyBike"));
        router.setWay(FORWARD);

        Bike bike = (Bike) request.getSession().getAttribute(BIKE);
        String field = request.getParameter(FIELD);
        String value = request.getParameter(VALUE);

        try {
            Map<String, Boolean> resultOfError = ModifyBikeService.getInstance()
                    .checkModifyBikeForm(field, value);
            if (resultOfError.containsValue(false)) {
                ModifyBikeService.getInstance().modifyBike(bike, field, value);
            } else {
                resultOfError.forEach(request::setAttribute);
            }
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return router;
    }

}
