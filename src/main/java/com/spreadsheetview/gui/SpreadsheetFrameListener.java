package com.spreadsheetview.gui;

import com.spreadsheetmodel.Spreadsheet;

public interface SpreadsheetFrameListener {

    /**
     * Listener notified that the spreadsheetFrame's state has changed.
     * @param s a Spreadsheet that the Listener is monitoring.
     */
    public void spreadsheetFrameChanged(final Spreadsheet s);
}