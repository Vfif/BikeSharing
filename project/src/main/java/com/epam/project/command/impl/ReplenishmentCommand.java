package com.epam.project.command.impl;

import com.epam.project.command.ActionCommand;
import com.epam.project.controller.PageInfo;
import com.epam.project.resource.ConfigurationManager;
import com.epam.project.type.PageChangeType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class ReplenishmentCommand implements ActionCommand {
    private static Logger Logger = LogManager.getLogger();

    @Override
    public PageInfo execute(HttpServletRequest request) {
        Logger.debug("Replenishment command");
        PageInfo pageInfo = new PageInfo();
        pageInfo.setPage(ConfigurationManager.getProperty("path.page.rent"));
       /* try {
            connectionFrom.setAutoCommit(false);
            connectionTo.setAutoCommit(false);
            connectionFrom.commit();
            connectionTo.commit();
        } catch (SQLException e) {
            connectionFrom.rollback();
            connectionTo.rollback();
        } finally {
            connectionFrom.setAutoCommit(true);
            connectionTo.setAutoCommit(true);
        }*/
        pageInfo.setWay(PageChangeType.FORWARD);
        return pageInfo;
    }
}
