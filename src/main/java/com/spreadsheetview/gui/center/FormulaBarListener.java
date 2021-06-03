package com.spreadsheetview.gui.center;

import com.spreadsheetmodel.Spreadsheet;

/**
 * The formula bar listeners.
 *
 * @author Di Pietro Enrico, Prendi Gerald.
 *
 */
public interface FormulaBarListener {

    /**
     * Listener notified that the formulaBar's state has changed.
     * @param s a Spreadsheet that the Listener is monitoring.
     */
    public void formulaBarChanged(final Spreadsheet s);
}
