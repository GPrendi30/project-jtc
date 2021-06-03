package com.spreadsheetmodel.commands;

import com.spreadsheetmodel.Spreadsheet;
import com.spreadsheetmodel.SpreadsheetException;
import com.spreadsheetmodel.cell.Cell;

public class CutCommand implements Command {

    private CopyPasteStack stack;
    private String savedContent;
    private Cell savedCell;

    /**
     * Creates a new CutCommand.
     * @param copyPasteStack the stack where it stores the value temporarily.
     */
    public CutCommand(final CopyPasteStack copyPasteStack) {
        stack = copyPasteStack;
        savedContent = null;
        savedCell = null;
    }

    @Override
    public void execute(final Spreadsheet receiver) throws CommandException {
        savedContent = receiver.getCurrentCell().getText();
        savedCell = receiver.getCurrentCell();
        try {
            receiver.updateCurrentCell("");
        } catch (SpreadsheetException exception) {
            throw new CommandException(exception.getMessage(), exception);
        }
        stack.push(savedContent);
    }

    @Override
    public void undo(final Spreadsheet receiver) throws CommandException {
        try {
            receiver.updateCell(savedCell, stack.peek());
        } catch (SpreadsheetException exception) {
            throw new CommandException(exception.getMessage(), exception);
        }

    }

    @Override
    public void redo(final Spreadsheet receiver) throws CommandException {
        savedContent = savedCell.getText();
        try {
            receiver.updateCell(savedCell, "");
        } catch (SpreadsheetException exception) {
            throw new CommandException(exception.getMessage(), exception);
        }
        stack.push(savedContent);
    }
}
