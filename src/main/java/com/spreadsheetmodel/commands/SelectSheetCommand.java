package com.spreadsheetmodel.commands;

import com.spreadsheetmodel.Spreadsheet;
import com.spreadsheetmodel.SpreadsheetException;

public class SelectSheetCommand implements Command {

    private String prevSheet;
    private String selectedSheet;

    /**
     * Creates a new SelectSheetCommand.
     * @param selectedSheet the sheet that will be selected.
     */
    public SelectSheetCommand(final String selectedSheet) {
        this.selectedSheet = selectedSheet;
    }


    @Override
    public void execute(final Spreadsheet receiver) throws CommandException {
        try {
            prevSheet = receiver.getCurrentSheetName();
            receiver.selectSheet(selectedSheet);
        } catch (SpreadsheetException exception) {
            throw new CommandException(exception.getMessage(), exception);
        }
    }

    @Override
    public void undo(final Spreadsheet receiver) throws CommandException {
        try {
            receiver.selectSheet(prevSheet);
        } catch (SpreadsheetException exception) {
            throw new CommandException(exception.getMessage(), exception);
        }
    }

    @Override
    public void redo(final Spreadsheet receiver) throws CommandException {
        try {
            receiver.selectSheet(selectedSheet);
        } catch (SpreadsheetException exception) {
            throw new CommandException(exception.getMessage(), exception);
        }
    }
}
