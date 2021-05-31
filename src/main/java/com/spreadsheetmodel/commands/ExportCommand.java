package com.spreadsheetmodel.commands;

import com.spreadsheetmodel.Spreadsheet;
import java.io.IOException;

public class ExportCommand implements Command {
    String path;

    public ExportCommand(String path) {
        this.path = path;
    }

    @Override
    public void execute(Spreadsheet model) {
        try {
            model.exportCsv(path);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
