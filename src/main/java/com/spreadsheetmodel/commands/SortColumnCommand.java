package com.spreadsheetmodel.commands;

import com.spreadsheetmodel.Spreadsheet;
import com.spreadsheetmodel.SpreadsheetException;
import com.spreadsheetmodel.sheet.Sheet;

import java.util.ArrayList;

public class SortColumnCommand implements Command {

    private final ArrayList<String> columnValues;
    private final int column;

    /**
     * Crates a new SortColumn command for a given int column.
     * @param column the column to be sorted.
     */
    public SortColumnCommand(final int column) {
        this.column = column;
        columnValues = new ArrayList<>();
    }

    @Override
    public void execute(final Spreadsheet receiver) throws CommandException {
        try {
            final Sheet s = receiver.getCurrentSheet();
            final int size = s.sizeX();

            for (int i = 0; i < size; i++) {
                columnValues.add(s.getCell(i, column).getText());
            }

            receiver.sortCol(column);
        } catch (SpreadsheetException exception) {
            throw new CommandException(exception.getMessage(), exception);
        }

    }

    @Override
    public void undo(final Spreadsheet receiver) throws CommandException {
        final Sheet s = receiver.getCurrentSheet();
        final int size = s.sizeX();

        try {
            for (int i = 0; i < size; i++) {
                receiver.updateCell(i, column, columnValues.get(i));
            }
        } catch (SpreadsheetException exception) {
            throw new CommandException(exception.getMessage(), exception);
        }

    }

    @Override
    public void redo(final Spreadsheet receiver) throws CommandException {
        try {
            receiver.sortCol(column);
        } catch (SpreadsheetException exception) {
            throw new CommandException(exception.getMessage(), exception);
        }
    }
}
