package com.spreadsheetmodel.commands;

import com.spreadsheetmodel.Spreadsheet;
import com.spreadsheetmodel.cell.Cell;

public class PasteCommand implements Command {

    private CopyPasteStack stack;
    private String oldContent;
    private Cell targetCell;

    public PasteCommand(final CopyPasteStack copyPasteStack) {
        stack = copyPasteStack;
    }

    @Override
    public void execute(final Spreadsheet receiver) {
        oldContent = receiver.getCurrentCell().getText();
        targetCell = receiver.getCurrentCell();
        receiver.updateCurrentCell(stack.peek());
    }

    @Override
    public void undo(final Spreadsheet receiver) {
        receiver.updateCell(targetCell, oldContent);
        stack.pop();
    }

    @Override
    public void redo(final Spreadsheet receiver) {
        oldContent = targetCell.getText();
        receiver.updateCurrentCell(stack.peek());
    }

}
