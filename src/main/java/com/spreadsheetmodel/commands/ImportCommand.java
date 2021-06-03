package com.spreadsheetmodel.commands;

import com.spreadsheetmodel.Spreadsheet;
import com.spreadsheetmodel.SpreadsheetException;

import com.spreadsheetmodel.sheet.Sheet;

import java.io.IOException;
import java.nio.file.Paths;

public class ImportCommand implements Command {

    private final String path;
    private Sheet importedSheet;
    private String prevSheet;

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
            prevSheet = receiver.getCurrentSheetName();
            receiver.importCsv(path);
            importedSheet = receiver.getCurrentSheet();
        } catch (IOException exception) {
            exception.printStackTrace();
        } catch (SpreadsheetException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void undo(final Spreadsheet receiver) {
        final String importSheet = Paths.get(path).getFileName().toString();
        try {
            receiver.removeSheet(importSheet);
        } catch (SpreadsheetException exception) {
            exception.printStackTrace();
        }
        receiver.selectSheet(prevSheet);
    }

    @Override
    public void redo(final Spreadsheet receiver) {
        receiver.addSheet(importedSheet);
    }
}
