package com.spreadsheetmodel.commands;

import com.spreadsheetmodel.Spreadsheet;

public class CopyCommand implements Command {

    private CopyPasteStack stack;
    private String savedContent;

    /**
     * Creates a new CopyCommand
     * @param copyPasteStack the stack where it stores the value temporarily.
     */
    public CopyCommand(final CopyPasteStack copyPasteStack) {
        stack = copyPasteStack;
        savedContent = null;
    }

    @Override
    public void execute(final Spreadsheet receiver) {
        updateContent(receiver.getCurrentCell().getText());
        stack.push(savedContent);
    }

    @Override
    public void undo(final Spreadsheet receiver) {
        stack.pop();
    }

    @Override
    public void redo(final Spreadsheet receiver) {
        stack.push(savedContent);
    }

    private void updateContent(final String content) {
        savedContent = content;
    }

}
