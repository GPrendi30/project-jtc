package com.spreadsheetview.tui;

import com.spreadsheetmodel.commands.Command;
import com.spreadsheetmodel.commands.CommandException;
import com.spreadsheetmodel.commands.Invoker;

public final class TuiCommandHandler {

    /**
     * Dummy constructor.
     */
    private TuiCommandHandler() {}

    /**
     * Handles a command for the TUI.
     * @param command a Command to be executed.
     */
    public static void handleCommand(final Command command) {
        try {
            Invoker.getInstance().invoke(command);
        } catch (CommandException exception) {
            System.out.println(exception.getMessage());
        }
    }

}
