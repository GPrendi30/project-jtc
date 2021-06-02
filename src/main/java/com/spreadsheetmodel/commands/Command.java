package com.spreadsheetmodel.commands;

import com.spreadsheetmodel.Spreadsheet;

public interface Command {

    /**
     * Executes a command
     * @param receiver the Spreadsheet were the commands will apply.
     */
    public void execute(final Spreadsheet receiver);

    /**
     * Undos a command
     * @param receiver the Spreadsheet were the commands will apply.
     */
    public void undo(final Spreadsheet receiver);

    /**
     * Executes a command
     * @param receiver the Spreadsheet were the commands will apply.
     */
    public void redo(final Spreadsheet receiver);
}
