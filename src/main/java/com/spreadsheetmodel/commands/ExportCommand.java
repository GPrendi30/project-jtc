package com.spreadsheetmodel.commands;

import com.spreadsheetmodel.Spreadsheet;

import com.spreadsheetmodel.SpreadsheetException;

import java.io.IOException;

public class ExportCommand implements Command {

    private final String path;

    /**
     * Creates a new Export Command
     * @param path the path to export to.
     */
    public ExportCommand(final String path) {
        this.path = path;
    }

    @Override
    public void execute(final Spreadsheet receiver) throws SpreadsheetException {
        try {
            receiver.exportCsv(path);
        } catch (IOException exception) {
            throw new SpreadsheetException(exception.getMessage(), exception);
        }
    }

    @Override
    public void undo(final Spreadsheet receiver) {
        //
    }

    @Override
    public void redo(final Spreadsheet receiver) {
        //
    }
}
