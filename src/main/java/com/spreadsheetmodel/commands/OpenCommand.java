package com.spreadsheetmodel.commands;

import com.spreadsheetmodel.Spreadsheet;

import com.spreadsheetmodel.SpreadsheetException;
import com.spreadsheetmodel.SpreadsheetIOEngine;
import java.io.IOException;

public class OpenCommand implements Command {

    private final String path;

    /**
     * Creates a new OpenCommand from a path.
     * @param path the path to open a saved File.
     */
    public OpenCommand(final String path) {
        this.path = path;
    }

    @Override
    public void execute(final Spreadsheet receiver) {
        try {
            SpreadsheetIOEngine.readFromFile(this.path);
        } catch (IOException exception) {
            exception.printStackTrace();
        } catch (SpreadsheetException exception) {
            exception.printStackTrace();
        }

    }

    @Override
    public void undo(final Spreadsheet receiver) {
        // doesnt have undo

    }

    @Override
    public void redo(final Spreadsheet receiver) {
        //doesnt have redo

    }
}
