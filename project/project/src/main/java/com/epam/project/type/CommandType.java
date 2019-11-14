package com.epam.project.type;

import com.epam.project.command.ActionCommand;
import com.epam.project.command.impl.LocaleCommand;
import com.epam.project.command.impl.LoginCommand;
import com.epam.project.command.impl.LogoutCommand;
import com.epam.project.command.impl.RegistrationCommand;

public enum CommandType {
    LOGIN {
        {
            this.command = new LoginCommand();
        }
    },
    LOGOUT {
        {
            this.command = new LogoutCommand();
        }
    },
    REGISTRATION {
        {
            this.command = new RegistrationCommand();
        }
    },
    LOCALE {
        {
            this.command = new LocaleCommand();
        }
    };
    ActionCommand command;

    public ActionCommand getCurrentCommand() {
        return command;
    }
}