package com.spreadsheetmodel.commands;

import com.spreadsheetmodel.Spreadsheet;
import com.spreadsheetmodel.SpreadsheetException;

public class AddNewSheetCommand implements Command {

    private String prevSheet;
    private String addedSheet;

    /**
     * Add new Sheet command.
     * @param addedSheet a String path.
     */
    public AddNewSheetCommand(final String addedSheet) {
        this.addedSheet = addedSheet;
    }

    @Override
    public void execute(final Spreadsheet receiver) throws CommandException {
        try {
            receiver.addNewSheet(addedSheet);
            prevSheet = receiver.getCurrentSheetName();
        } catch (SpreadsheetException exception) {
            throw new CommandException(exception.getMessage(), exception);
        }
    }

    @Override
    public void undo(final Spreadsheet receiver) throws CommandException {
        try {
            receiver.removeSheet(addedSheet);
            receiver.selectSheet(prevSheet);
        } catch (SpreadsheetException exception) {
            throw new CommandException(exception.getMessage(), exception);
        }
    }

    @Override
    public void redo(final Spreadsheet receiver) throws CommandException {
        try {
            receiver.addNewSheet(addedSheet);
        } catch (SpreadsheetException exception) {
            throw new CommandException(exception.getMessage(), exception);
        }
    }
}
