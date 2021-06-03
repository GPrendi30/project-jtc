package com.spreadsheetmodel.commands;

import com.spreadsheetmodel.Spreadsheet;

import com.spreadsheetmodel.SpreadsheetException;
import com.spreadsheetmodel.SpreadsheetIO;

import com.spreadsheetview.SpreadsheetView;

import java.io.IOException;

/**
 * The open command.
 *
 * @author Di Pietro Enrico, Prendi Gerald.
 *
 */
public class OpenCommand implements Command {

    private final String path;
    private final SpreadsheetView target;

    /**
     * Creates a new OpenCommand from a path.
     * @param path the path to open a saved File.
     * @param target a SpreadsheetView that will update a model.
     */
    public OpenCommand(final String path, final SpreadsheetView target) {
        this.target = target;
        this.path = path;
    }

    @Override
    public void execute(final Spreadsheet receiver) throws SpreadsheetException  {
        try {
            final Spreadsheet temp = SpreadsheetIO.readFromFile(this.path);
            target.updateModel(temp);
        } catch (IOException exception) {
            throw new SpreadsheetException(exception.getMessage(), exception);
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
