package com.spreadsheetmodel.commands;

import com.spreadsheetmodel.Spreadsheet;
import com.spreadsheetmodel.cell.Cell;

public class CutCommand implements Command {

    private static CopyPasteStack stack;
    private String savedContent;
    private Cell savedCell;

    public CutCommand(final CopyPasteStack copyPasteStack) {
        stack = copyPasteStack;
    }

    @Override
    public void execute(final Spreadsheet receiver) {
        savedContent = receiver.getCurrentCell().getText();
        savedCell = receiver.getCurrentCell();
        receiver.updateCurrentCell("");
        stack.push(savedContent);
    }

    @Override
    public void undo(final Spreadsheet receiver) {
        receiver.updateCell(savedCell, stack.pop());

    }

    @Override
    public void redo(final Spreadsheet receiver) {
        savedContent = savedCell.getText();
        receiver.updateCell(savedCell, "");
        stack.push(savedContent);
    }
}
