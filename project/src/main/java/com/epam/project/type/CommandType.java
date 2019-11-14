package com.epam.project.type;

import com.epam.project.command.ActionCommand;
import com.epam.project.command.impl.*;

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
    },
    POSITIONING {
        {
            this.command = new PositioningCommand();
        }
    },
    REPLENISHMENT {
        {
            this.command = new ReplenishmentCommand();
        }
    },
    RENT{
        {
            this.command = new RentCommand();
        }
    },
    PAY{
        {
            this.command = new PayCommand();
        }
    },
    ADD_BIKE{
        {
            this.command = new AddBikeCommand();
        }
    },
    SAVE_STATUS{
        {
            this.command = new SaveStatusCommand();
        }
    },
    GET_USER_LIST{
        {
            this.command = new GetUserListCommand();
        }
    };
    ActionCommand command;

    public ActionCommand getCurrentCommand() {
        return command;
    }
}