package com.epam.project.controller;

import com.epam.project.command.ActionCommand;
import com.epam.project.command.ActionFactory;
import com.epam.project.exception.CommandException;
import com.epam.project.resource.ConfigurationManager;
import com.epam.project.type.PageChangeType;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/controller")
public class Controller extends HttpServlet {
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
        //ConnectionPool.getInstance().destroyPool();
    }
}