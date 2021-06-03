package com.spreadsheetview.gui;

import com.spreadsheetmodel.commands.Command;
import com.spreadsheetmodel.commands.CommandException;
import com.spreadsheetmodel.commands.Invoker;

import javax.swing.JOptionPane;

/**
 * The GuiHandlerUtil.
 *
 * @author Di Pietro Enrico, Prendi Gerald.
 *
 */
public final class GuiHandlerUtil {

    private GuiHandlerUtil() {}

    /**
     * Handle a command for the GUI.
     * @param command a Command to be executed.
     */
    public static void handleCommand(final Command command) {
        try {
            Invoker.getInstance().invoke(command);
        } catch (CommandException exception) {
            JOptionPane.showMessageDialog(null, exception.getMessage());
        }
    }

}
