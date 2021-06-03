package com.spreadsheetmodel.commands;

import com.spreadsheetmodel.Spreadsheet;
import com.spreadsheetmodel.SpreadsheetException;
import com.spreadsheetmodel.cell.Cell;

public class PasteCommand implements Command {

    private CopyPasteStack stack;
    private String oldContent;
    private Cell targetCell;

    /**
     * Creates a new PasteCommand.
     * @param copyPasteStack the stack where it stores the value.
     */
    public PasteCommand(final CopyPasteStack copyPasteStack) {
        stack = copyPasteStack;
    }

    @Override
    public void execute(final Spreadsheet receiver) throws CommandException {
        try {
            oldContent = receiver.getCurrentCell().getText();
            targetCell = receiver.getCurrentCell();
            receiver.updateCurrentCell(stack.peek());
        } catch (SpreadsheetException exception) {
            throw new CommandException(exception.getMessage(), exception);
        }
    }

    @Override
    public void redo(final Spreadsheet receiver) throws CommandException {

        try {
            oldContent = targetCell.getText();
            receiver.updateCurrentCell(stack.peek());
        } catch (SpreadsheetException exception) {
            throw new CommandException(exception.getMessage(), exception);
        }
    }

    @Override
    public void undo(final Spreadsheet receiver) throws CommandException {
        try {
            receiver.updateCell(targetCell, oldContent);
        } catch (SpreadsheetException exception) {
            throw new CommandException(exception.getMessage(), exception);
        }
    }

}
