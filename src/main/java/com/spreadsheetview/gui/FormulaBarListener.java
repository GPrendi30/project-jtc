package com.spreadsheetview.gui;

import com.spreadsheetmodel.*;

public interface FormulaBarListener {

    /**
     * Listener notified that the formulaBar's state has changed.
     * @param s a Spreadsheet that the Listener is monitoring.
     */
    public void formulaBarChanged(final Spreadsheet s);
}
