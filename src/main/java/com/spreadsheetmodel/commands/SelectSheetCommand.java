package com.spreadsheetmodel.commands;

import com.spreadsheetmodel.Spreadsheet;
import com.spreadsheetmodel.SpreadsheetException;

/**
 * The select sheet command.
 *
 * @author Di Pietro Enrico, Prendi Gerald.
 *
 */
public class SelectSheetCommand implements Command, UndoableCommand {

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
    public void execute(final Spreadsheet receiver) throws SpreadsheetException {
        prevSheet = receiver.getCurrentSheetName();
        receiver.selectSheet(selectedSheet);
    }

    @Override
    public void undo(final Spreadsheet receiver) throws SpreadsheetException {
        receiver.selectSheet(prevSheet);
    }

    @Override
    public void redo(final Spreadsheet receiver) throws SpreadsheetException {
        receiver.selectSheet(selectedSheet);

    }
}
