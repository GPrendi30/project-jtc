package com.spreadsheetmodel.commands;

import com.spreadsheetmodel.Spreadsheet;

public interface Command {

    public void execute(Spreadsheet model);
}
