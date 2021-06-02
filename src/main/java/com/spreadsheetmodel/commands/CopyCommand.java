package com.spreadsheetmodel.commands;

import com.spreadsheetmodel.Spreadsheet;
import com.spreadsheetmodel.cell.Cell;

public class CopyCommand implements Command {

    private static CopyPasteStack stack;
    private String savedContent;
    private Cell savedCell;

    public CopyCommand(final CopyPasteStack copyPasteStack) {
        stack = copyPasteStack;
    }

    @Override
    public void execute(Spreadsheet receiver) {
        savedContent = receiver.getCurrentCell().getText();
        savedCell = receiver.getCurrentCell();
        stack.push(savedContent);
    }

    @Override
    public void undo(Spreadsheet receiver) {
        //stack.pop();
    }

    @Override
    public void redo(Spreadsheet receiver) {
        //stack.push(savedContent);
    }
}
