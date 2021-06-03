package com.spreadsheetmodel;

import java.io.Serializable;

/**
 * The listeners for the class Spreadsheet.
 *
 * @author Di Pietro Enrico, Prendi Gerald.
 *
 */
public interface SpreadsheetListener extends Serializable {

    /**
     * Listener notified that the spreadsheet's state has changed.
     * @param s a Spreadsheet that the Listener is monitoring.
     * @param se a SpreadsheetEvent.
     */
    public void spreadsheetChanged(final Spreadsheet s, SpreadsheetEvent se);
}
