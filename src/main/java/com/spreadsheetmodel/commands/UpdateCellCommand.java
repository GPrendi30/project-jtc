package com.spreadsheetmodel.commands;

import com.spreadsheetmodel.Spreadsheet;
import com.spreadsheetmodel.SpreadsheetException;
import com.spreadsheetmodel.cell.Cell;

public class UpdateCellCommand implements Command {

    private Cell cell;
    private String prevContent;
    private String content;

    /**
     * Creates a new UpdateCellCommand.
     * @param c a Cell to be updated.
     * @param content the String content to be written.
     */
    public UpdateCellCommand(final Cell c, final String content) {
        this.cell = c;
        this.content = content;
    }

    @Override
    public void execute(final Spreadsheet receiver) throws CommandException {
        try {
            this.prevContent = cell.getText();
            receiver.updateCell(cell, content);
        } catch (SpreadsheetException exception) {
            throw new CommandException(exception.getMessage(), exception);
        }
    }

    @Override
    public void undo(final Spreadsheet receiver) throws CommandException {
        try {
            receiver.updateCell(cell, prevContent);
        } catch (SpreadsheetException exception) {
            throw new CommandException(exception.getMessage(), exception);
        }
    }

    @Override
    public void redo(final Spreadsheet receiver) throws CommandException {
        execute(receiver);
    }
}
