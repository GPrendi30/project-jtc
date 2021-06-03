package com.spreadsheetmodel.commands;

import com.spreadsheetmodel.Spreadsheet;
import com.spreadsheetmodel.SpreadsheetException;

/**
 * The command to turn the formulas on.
 *
 * @author Di Pietro Enrico, Prendi Gerald.
 *
 */
public class FormulasOnCommand implements Command, UndoableCommand {

    @Override
    public void execute(final Spreadsheet receiver) throws SpreadsheetException {
        receiver.formulasOn();
    }

    @Override
    public void undo(final Spreadsheet receiver) throws SpreadsheetException {
        receiver.getCurrentSheet().reEvalFormulas();
    }

    @Override
    public void redo(final Spreadsheet receiver) throws SpreadsheetException {
        execute(receiver);
    }
}
