package com.spreadsheetmodel.commands;

import com.spreadsheetmodel.Spreadsheet;

public class ImportCommand implements Command {

    String path;

    public void ImportCommand() {

    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public void execute(Spreadsheet model) {
        model.importCsv(path);
    }
}
