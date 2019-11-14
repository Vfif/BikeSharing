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

@WebServlet("/controller")
@MultipartConfig(fileSizeThreshold = 1024 * 1024
        , maxFileSize = 1024 * 1024 * 5
        , maxRequestSize = 1024 * 1024 * 5 * 5)
public class Controller extends HttpServlet {
    private static Logger Logger = LogManager.getLogger();
    private static ActionFactory actionFactory = ActionFactory.getInstance();
    private static final String COMMAND = "command";
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String commandString = request.getParameter(COMMAND);
        ActionCommand command;
        PageInfo pageInfo;
        try {
            command = actionFactory.defineCommand(commandString);
            pageInfo = command.execute(request);
        } catch (CommandException ex) {
            pageInfo = new PageInfo();
            pageInfo.setPage(ConfigurationManager.getProperty("path.page.error"));
            pageInfo.setWay(PageChangeType.REDIRECT);
            request.getSession().setAttribute("nullPage","Page not found. Business logic error.");
        }

        if (pageInfo.getWay() == PageChangeType.FORWARD) {
            getServletContext()
                    .getRequestDispatcher(pageInfo.getPage())
                    .forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + pageInfo.getPage());
        }
    }

    @Override
    public void destroy() {
        super.destroy();
        try {
            ConnectionPool.getInstance().destroyPool();
        } catch (ConnectionPoolException e) {
            Logger.error("Cannot destroy connection pool", e);
        }
    }
}