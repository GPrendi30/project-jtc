package com.spreadsheetmodel;

public interface SpreadsheetListener {

    /**
     * Listener notified that the spreadsheet's state has changed.
     * @param s a Spreadsheet that the Listener is monitoring.
     * @param se a SpreadsheetEvent.
     */
    public void spreadsheetChanged(final Spreadsheet s, SpreadsheetEvent se);
}
