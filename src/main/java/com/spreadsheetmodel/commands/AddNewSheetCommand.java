package com.spreadsheetmodel.commands;

import com.spreadsheetmodel.Spreadsheet;

public class AddNewSheetCommand implements Command {

    private String prevSheet;
    private String addedSheet;

    /**
     * Add new SHeet command.
     * @param addedSheet a String path.
     */
    public AddNewSheetCommand(final String addedSheet) {
        this.addedSheet = addedSheet;
    }

    @Override
    public void execute(final Spreadsheet receiver) {
        prevSheet = receiver.getCurrentSheetName();
        receiver.addNewSheet(addedSheet);
    }

    @Override
    public void undo(final Spreadsheet receiver) {
        receiver.removeSheet(addedSheet);
        receiver.selectSheet(prevSheet);
    }

    @Override
    public void redo(final Spreadsheet receiver) {
        receiver.addNewSheet(addedSheet);
    }
}
