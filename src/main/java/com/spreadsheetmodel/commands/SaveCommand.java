package com.spreadsheetmodel.commands;

import com.spreadsheetmodel.Spreadsheet;

import com.spreadsheetmodel.SpreadsheetIOEngine;
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
            SpreadsheetIOEngine.writeToFile(path, receiver);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void undo(final Spreadsheet receiver) {
        //empty
        // throw you cannot undo this operation.
    }

    @Override
    public void redo(final Spreadsheet receiver) {
        //empty
    }
}
