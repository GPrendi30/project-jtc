package com.spreadsheetmodel.commands;

import com.spreadsheetmodel.Spreadsheet;

public interface Command {

    /**
     * Executes a command
     * @param receiver the Spreadsheet were the commands will apply.
     * @throws CommandException if the command has an exception.
     */
    public void execute(final Spreadsheet receiver) throws CommandException;

    /**
     * Undos a command
     * @param receiver the Spreadsheet were the commands will apply.
     * @throws CommandException if the command has an exception.
     */
    public void undo(final Spreadsheet receiver) throws CommandException;

    /**
     * Executes a command
     * @param receiver the Spreadsheet were the commands will apply.
     * @throws CommandException if the command has an exception.
     */
    public void redo(final Spreadsheet receiver) throws CommandException;
}
