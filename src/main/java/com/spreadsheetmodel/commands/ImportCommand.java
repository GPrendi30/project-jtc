package com.spreadsheetmodel.commands;

import com.spreadsheetmodel.Spreadsheet;

public class ImportCommand implements Command {

    String path;

    /**
     * Creates a new ImportCommand.
     * @param path the path to import from.
     */
    public ImportCommand(String path) {
        this.path = path;
    }

    @Override
    public void execute(Spreadsheet model) {
        model.importCsv(path);
    }
}
