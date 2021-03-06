package com.spreadsheetmodel.commands;

import com.spreadsheetmodel.Spreadsheet;
import com.spreadsheetmodel.SpreadsheetException;

/**
 * The Command class that contains the basic methods
 * for the commands.
 *
 * @author Di Pietro Enrico, Prendi Gerald.
 *
 */
public interface Command {

    /**
     * Executes a command
     * @param receiver the Spreadsheet were the commands will apply.
     * @throws SpreadsheetException if the command has an exception.
     */
    public void execute(final Spreadsheet receiver) throws SpreadsheetException;

    /**
     * Undos a command
     * @param receiver the Spreadsheet were the commands will apply.
     * @throws SpreadsheetException if the command has an exception.
     */
    public void undo(final Spreadsheet receiver) throws SpreadsheetException;

    /**
     * Executes a command
     * @param receiver the Spreadsheet were the commands will apply.
     * @throws SpreadsheetException if the command has an exception.
     */
    public void redo(final Spreadsheet receiver) throws SpreadsheetException;
}
