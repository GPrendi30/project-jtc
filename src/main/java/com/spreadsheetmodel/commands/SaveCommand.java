package com.spreadsheetmodel.commands;

import com.spreadsheetmodel.Spreadsheet;

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
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
