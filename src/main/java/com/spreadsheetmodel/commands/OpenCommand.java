package com.spreadsheetmodel.commands;

import com.spreadsheetmodel.Spreadsheet;

import com.spreadsheetmodel.SpreadsheetException;
import com.spreadsheetmodel.SpreadsheetIO;

import com.spreadsheetview.SpreadsheetView;
import java.io.IOException;

public class OpenCommand implements Command {

    private final String path;
    private final SpreadsheetView target;

    /**
     * Creates a new OpenCommand from a path.
     * @param path the path to open a saved File.
     */
    public OpenCommand(final String path, final SpreadsheetView target) {
        this.target = target;
        this.path = path;
    }

    @Override
    public void execute(final Spreadsheet receiver) {
        try {
            final Spreadsheet temp = SpreadsheetIO.readFromFile(this.path);
            target.updateModel(temp);
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
