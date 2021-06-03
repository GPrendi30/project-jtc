package com.spreadsheetmodel.commands;

import com.spreadsheetmodel.Spreadsheet;
import com.spreadsheetmodel.SpreadsheetException;
import com.spreadsheetmodel.cell.Cell;

public class CutCommand implements Command, UndoableCommand {

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
    public void execute(final Spreadsheet receiver) throws SpreadsheetException {
        savedContent = receiver.getCurrentCell().getText();
        savedCell = receiver.getCurrentCell();
        receiver.updateCurrentCell("");
        stack.push(savedContent);
    }

    @Override
    public void undo(final Spreadsheet receiver) throws SpreadsheetException {
        receiver.updateCell(savedCell, stack.peek());
    }

    @Override
    public void redo(final Spreadsheet receiver) throws SpreadsheetException {
        savedContent = savedCell.getText();
        receiver.updateCell(savedCell, "");
        stack.push(savedContent);
    }
}
