package com.spreadsheetmodel.commands;

import com.spreadsheetmodel.Spreadsheet;

import java.io.IOException;

public class SaveCommand implements Command {

    private final String path;

    /**
     * Creates a new SaveCommand.
     * @param path the path to save the File.
     */
    public SaveCommand(final String path) {
        this.path = path;
    }

    @Override
    public void execute(final Spreadsheet receiver) {
        try {
            Spreadsheet.writeToFile(path, receiver);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void undo(Spreadsheet receiver) {
        //empty
        // throw you cannot undo this operation.
    }

    @Override
    public void redo(Spreadsheet receiver) {
        //empty
    }
}
