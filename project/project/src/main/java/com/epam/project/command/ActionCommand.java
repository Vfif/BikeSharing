package com.epam.project.command;

import com.epam.project.controller.PageInfo;
import com.epam.project.exception.CommandException;

import javax.servlet.http.HttpServletRequest;

public interface ActionCommand {
    PageInfo execute(HttpServletRequest request) throws CommandException;
}