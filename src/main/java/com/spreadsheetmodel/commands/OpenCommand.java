package com.spreadsheetmodel.commands;

import com.spreadsheetmodel.Spreadsheet;

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
    public void execute(Spreadsheet model) {
        try {
            model = Spreadsheet.readFromFile(this.path);
        } catch (Exception exception) {
            exception.printStackTrace();
        }

    }
}
