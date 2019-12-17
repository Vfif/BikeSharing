package com.epam.project.controller;

import com.epam.project.command.ActionCommand;
import com.epam.project.command.ActionFactory;
import com.epam.project.connection.ConnectionPool;
import com.epam.project.exception.CommandException;
import com.epam.project.exception.ConnectionPoolException;
import com.epam.project.resource.ConfigurationManager;
import com.epam.project.type.PageChangeType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "bikeSharing", urlPatterns = {"/controller"})
@MultipartConfig(fileSizeThreshold = 1024 * 1024
        , maxFileSize = 1024 * 1024 * 5
        , maxRequestSize = 1024 * 1024 * 5 * 5)
public class Controller extends HttpServlet {
    private static Logger Logger = LogManager.getLogger();
    private static ActionFactory actionFactory = ActionFactory.getInstance();
    private static final String COMMAND = "command";
    private static final String ERROR = "error";
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String commandString = request.getParameter(COMMAND);
        ActionCommand command;
        Router router;
        try {
            command = actionFactory.defineCommand(commandString);
            router = command.execute(request);
        } catch (CommandException ex) {
            router = new Router();
            router.setPage(ConfigurationManager.getProperty("path.page.error"));
            router.setWay(PageChangeType.REDIRECT);
            request.getSession().setAttribute(ERROR, ex);
        }

        if (router.getWay() == PageChangeType.FORWARD) {
            getServletContext()
                    .getRequestDispatcher(router.getPage())
                    .forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + router.getPage());
        }
    }

    @Override
    public void destroy() {

        try {
            ConnectionPool.getInstance().destroyPool();
        } catch (ConnectionPoolException e) {
            Logger.error("Cannot destroy connection pool", e);
        }
        super.destroy();
    }
}