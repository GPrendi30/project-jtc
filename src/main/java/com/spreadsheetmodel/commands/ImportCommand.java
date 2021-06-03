package com.spreadsheetmodel.commands;

import com.spreadsheetmodel.Spreadsheet;
import com.spreadsheetmodel.SpreadsheetException;

import com.spreadsheetmodel.sheet.Sheet;

import java.io.IOException;
import java.nio.file.Paths;

public class ImportCommand implements Command, UndoableCommand {

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
    public void execute(final Spreadsheet receiver) throws SpreadsheetException {
        prevSheet = receiver.getCurrentSheetName();
        try {
            receiver.importCsv(path);
        } catch (IOException exception) {
            throw new SpreadsheetException(exception.getMessage(),exception);
        }
        importedSheet = receiver.getCurrentSheet();
    }

    @Override
    public void undo(final Spreadsheet receiver) throws SpreadsheetException {
        receiver.removeSheet(Paths.get(path).getFileName().toString());
        receiver.selectSheet(prevSheet);
    }

    @Override
    public void redo(final Spreadsheet receiver) throws SpreadsheetException {
        receiver.addSheet(importedSheet);
    }
}
