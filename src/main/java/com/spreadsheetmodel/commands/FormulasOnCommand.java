package com.spreadsheetmodel.commands;

import com.spreadsheetmodel.Spreadsheet;

public class FormulasOnCommand implements Command {

    @Override
    public void execute(final Spreadsheet receiver) {
        receiver.formulasOn();
    }

    @Override
    public void undo(final Spreadsheet receiver) {
        receiver.updateCurrentCell("");
    }

    @Override
    public void redo(final Spreadsheet receiver) {
        execute(receiver);
    }
}
