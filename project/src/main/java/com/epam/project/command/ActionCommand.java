package com.epam.project.command;

import com.epam.project.command.impl.*;
import com.epam.project.controller.Router;
import com.epam.project.exception.CommandException;

import javax.servlet.http.HttpServletRequest;

/**
 * The interface Action command.
 * Perform action according to command that was received from servlet.
 *
 * @see         AddBikeCommand
 * @see         ChangeBikeCommand
 * @see         DeleteBikeCommand
 * @see         GetBikeListCommand
 * @see         GetUserListCommand
 * @see         GoToCommand
 * @see         HistoryCommand
 * @see         LocaleCommand
 * @see         LoginCommand
 * @see         LogoutCommand
 * @see         ModifyBikeCommand
 * @see         PayCommand
 * @see         PositioningCommand
 * @see         RateCommand
 * @see         RegistrationCommand
 * @see         RentCommand
 * @see         ReplenishmentCommand
 * @see         SaveUserStatusCommand
 * @author      Mariya Gurskaya
 * @since       1.0
 */
public interface ActionCommand {
    /**
     * Add information to request, form router.
     * Can apply to service for extra information.
     *
     * @param request the request
     * @return the router: information about new page and way of page change
     * @throws CommandException the command exception if command cannot executed
     */
    Router execute(HttpServletRequest request) throws CommandException;
}