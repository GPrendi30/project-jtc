package com.spreadsheetmodel.commands;

import com.spreadsheetmodel.Spreadsheet;
import com.spreadsheetmodel.SpreadsheetException;

public class GrowSheetCommand implements Command {

    private String direction;
    private int size;

    /**
     * Creates a new GrowSheetCommand
     * @param direction a String direction.
     * @param size an int size.
     */
    public GrowSheetCommand(final String direction, final int size) {
        this.direction = direction;
        this.size = size;
    }

    @Override
    public void execute(final Spreadsheet receiver) throws SpreadsheetException {
        receiver.grow(this.direction, this.size);
    }

    @Override
    public void undo(final Spreadsheet receiver) throws SpreadsheetException {
        //
    }

    @Override
    public void redo(final Spreadsheet receiver) throws SpreadsheetException {
        //
    }
}
