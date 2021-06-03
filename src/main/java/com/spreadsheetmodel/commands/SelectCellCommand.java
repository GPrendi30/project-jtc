package com.spreadsheetmodel.commands;

import com.spreadsheetmodel.Spreadsheet;
import com.spreadsheetmodel.SpreadsheetException;
import com.spreadsheetmodel.cell.CellLocation;

public class SelectCellCommand implements Command {

    private CellLocation prevCellLocation;

    private int x;
    private int y;

    /**
     * Create a new SelectCellCommand.
     * @param x the x coordinate.
     * @param y the y coordinate.
     */
    public SelectCellCommand(final int x, final int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Create a new SelectCellCommand.
     * @param location a String location.
     */
    public SelectCellCommand(final String location) {
        final int[] coordinates = CellLocation.parse(location);
        this.x = coordinates[0];
        this.y = coordinates[1];
    }

    @Override
    public void execute(final Spreadsheet receiver) throws CommandException {
        prevCellLocation = receiver.getCurrentCell().getLocation();
        try {
            receiver.selectCell(x,y);
        } catch (SpreadsheetException exception) {
            throw new CommandException(exception.getMessage(), exception);
        }
    }

    @Override
    public void undo(final Spreadsheet receiver) throws CommandException {
        try {
            receiver.selectCell(prevCellLocation.toString());
        } catch (SpreadsheetException exception) {
            throw new CommandException(exception.getMessage(), exception);
        }
    }

    @Override
    public void redo(final Spreadsheet receiver) throws CommandException {
        execute(receiver);
    }
}
