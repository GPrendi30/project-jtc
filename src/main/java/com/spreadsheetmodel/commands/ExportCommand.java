package com.spreadsheetmodel.commands;

import com.spreadsheetmodel.Spreadsheet;

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
    public void execute(final Spreadsheet receiver) {
        try {
            receiver.exportCsv(path);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void undo(Spreadsheet receiver) {
        //
    }

    @Override
    public void redo(Spreadsheet receiver) {
        //
    }
}
