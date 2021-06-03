package com.spreadsheetmodel.commands;

import com.spreadsheetmodel.Spreadsheet;
import com.spreadsheetmodel.SpreadsheetException;
import com.spreadsheetmodel.cell.Cell;

/**
 * The update cell command.
 *
 * @author Di Pietro Enrico, Prendi Gerald.
 *
 */
public class UpdateCellCommand implements Command, UndoableCommand {

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
    public void execute(final Spreadsheet receiver) throws SpreadsheetException {
        this.prevContent = cell.getText();
        receiver.updateCell(cell, content);
    }

    @Override
    public void undo(final Spreadsheet receiver) throws SpreadsheetException {
        receiver.updateCell(cell, prevContent);
    }

    @Override
    public void redo(final Spreadsheet receiver) throws SpreadsheetException {
        execute(receiver);
    }
}
