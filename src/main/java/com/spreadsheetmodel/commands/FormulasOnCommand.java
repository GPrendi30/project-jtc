package com.spreadsheetmodel.commands;

import com.spreadsheetmodel.Spreadsheet;
import com.spreadsheetmodel.SpreadsheetException;

public class FormulasOnCommand implements Command {

    @Override
    public void execute(final Spreadsheet receiver) throws CommandException {
        try {
            receiver.formulasOn();
        } catch (SpreadsheetException exception) {
            throw new CommandException(exception.getMessage(), exception);
        }
    }

    @Override
    public void undo(final Spreadsheet receiver) throws CommandException {
        try {
            receiver.getCurrentSheet().reEvalFormulas();
        } catch (SpreadsheetException exception) {
            throw new CommandException(exception.getMessage(), exception);
        }
    }

    @Override
    public void redo(final Spreadsheet receiver) throws CommandException {
        execute(receiver);
    }
}
