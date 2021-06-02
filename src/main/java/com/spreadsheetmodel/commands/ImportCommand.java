package com.spreadsheetmodel.commands;

import com.spreadsheetmodel.Spreadsheet;
import com.spreadsheetmodel.SpreadsheetException;
import com.spreadsheetmodel.sheet.Sheet;

import java.io.IOException;

public class ImportCommand implements Command {

    private final String path;
    private Sheet savedSheet;

    /**
     * Creates a new ImportCommand.
     * @param path the path to import from.
     */
    public ImportCommand(final String path) {
        this.path = path;
    }

    @Override
    public void execute(final Spreadsheet receiver) {
        try {
            receiver.importCsv(path);
            savedSheet = receiver.getCurrentSheet();
        } catch (IOException exception) {
            exception.printStackTrace();
        } catch (SpreadsheetException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void undo(final Spreadsheet receiver) {
        receiver.removeSheet(savedSheet);
    }

    @Override
    public void redo(final Spreadsheet receiver) {
        receiver.addSheet(savedSheet);
    }
}
