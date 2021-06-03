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
    public void execute(final Spreadsheet receiver) throws SpreadsheetException {
        receiver.addNewSheet(addedSheet);
        prevSheet = receiver.getCurrentSheetName();
    }

    @Override
    public void undo(final Spreadsheet receiver) throws SpreadsheetException {
        receiver.removeSheet(addedSheet);
        receiver.selectSheet(prevSheet);
    }

    @Override
    public void redo(final Spreadsheet receiver) throws SpreadsheetException {
        receiver.addNewSheet(addedSheet);
    }
}
