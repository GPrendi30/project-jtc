package com.spreadsheetmodel.commands;

import com.spreadsheetmodel.Spreadsheet;

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
    public void execute(final Spreadsheet receiver) {
        prevSheet = receiver.getCurrentSheetName();
        receiver.selectSheet(selectedSheet);
    }

    @Override
    public void undo(final Spreadsheet receiver) {
        receiver.selectSheet(prevSheet);
    }

    @Override
    public void redo(final Spreadsheet receiver) {
        receiver.selectSheet(selectedSheet);
    }
}
