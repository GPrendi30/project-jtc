package com.spreadsheetmodel.commands;

import com.spreadsheetmodel.Spreadsheet;
import com.spreadsheetmodel.SpreadsheetException;
import com.spreadsheetmodel.cell.Cell;

public class PasteCommand implements Command, UndoableCommand {

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
    public void execute(final Spreadsheet receiver) throws SpreadsheetException {
        oldContent = receiver.getCurrentCell().getText();
        targetCell = receiver.getCurrentCell();
        receiver.updateCell(targetCell, stack.peek());
    }

    @Override
    public void redo(final Spreadsheet receiver) throws SpreadsheetException {
        oldContent = targetCell.getText();
        receiver.updateCurrentCell(stack.peek());
    }

    @Override
    public void undo(final Spreadsheet receiver) throws SpreadsheetException {
        receiver.updateCell(targetCell, oldContent);
    }

}
