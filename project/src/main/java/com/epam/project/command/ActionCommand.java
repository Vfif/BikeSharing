package com.epam.project.command;

import com.epam.project.controller.Router;
import com.epam.project.exception.CommandException;

import javax.servlet.http.HttpServletRequest;

public interface ActionCommand {
    Router execute(HttpServletRequest request) throws CommandException;
}