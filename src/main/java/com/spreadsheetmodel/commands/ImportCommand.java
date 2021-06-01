package com.spreadsheetmodel.commands;

import com.spreadsheetmodel.Spreadsheet;

public class ImportCommand implements Command {

    private final String path;

    /**
     * Creates a new ImportCommand.
     * @param path the path to import from.
     */
    public ImportCommand(final String path) {
        this.path = path;
    }

    @Override
    public void execute(final Spreadsheet receiver) {
        receiver.importCsv(path);
    }
}
