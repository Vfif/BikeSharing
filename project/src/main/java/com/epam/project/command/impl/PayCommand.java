package com.epam.project.command.impl;

import com.epam.project.command.ActionCommand;
import com.epam.project.controller.PageInfo;
import com.epam.project.exception.CommandException;
import com.epam.project.exception.ServiceException;
import com.epam.project.resource.ConfigurationManager;
import com.epam.project.service.PayService;
import com.epam.project.service.RentService;
import com.epam.project.type.PageChangeType;
import com.epam.project.validation.PayValidation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

public class PayCommand implements ActionCommand {
    private static Logger Logger = LogManager.getLogger();

    @Override
    public PageInfo execute(HttpServletRequest request) throws CommandException {
        Logger.debug("Pay command");
        PageInfo pageInfo = new PageInfo();
        pageInfo.setWay(PageChangeType.FORWARD);
        long rentTime = (long)request.getAttribute("rentTime");
        double cost = (double)request.getAttribute("cost");
        double cash = (double)request.getAttribute("cash");
        long currentTime = new Date().getTime();

        if(PayValidation.isEnoughMoney(currentTime, rentTime, cash, cost)){
            pageInfo.setPage(ConfigurationManager.getProperty("path.page.end"));
            try {
                int bikeId = (int) request.getSession().getAttribute("id");
                String login = (String) request.getSession().getAttribute("login");
                PayService.payForRental(login, currentTime, rentTime, cash, cost);//снять деньги
                RentService.updateUserByBikeId(login, -1);//reset user bikeId
                RentService.updateBikeRentTime(bikeId, 0);//reset bike rentTime
            }catch (ServiceException e){
                Logger.error(e);
                throw new CommandException(e);
            }
        }else{
            request.setAttribute("invalid", true);
            pageInfo.setPage(ConfigurationManager.getProperty("path.page.rent"));
        }
        return pageInfo;
    }
}