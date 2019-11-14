package com.epam.project.command;

import com.epam.project.exception.CommandException;
import com.epam.project.type.CommandType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ActionFactory {
    private final static ActionFactory instance = new ActionFactory();
    private static Logger Logger = LogManager.getLogger();
    private ActionFactory() {
    }

    public static ActionFactory getInstance() {
        return instance;
    }

    public ActionCommand defineCommand(String commandName) throws CommandException {
        if (commandName == null || commandName.isEmpty()) {
            Logger.error("Unidentified command" + commandName);
            throw new CommandException("Unidentified command");
        }

        ActionCommand current;
        try {
            CommandType currentEnum = CommandType.valueOf(commandName.toUpperCase());
            current = currentEnum.getCurrentCommand();
        } catch (IllegalArgumentException e) {
           Logger.error("Unidentified command" + commandName);
           throw new CommandException("Unidentified command", e);
        }
        return current;
    }
}