package com.spreadsheetview.gui.center;

import com.spreadsheetmodel.Spreadsheet;

public interface SheetViewListener {

    /**
     * Listener notified that the sheetView's state has changed.
     * @param s a Spreadsheet that the Listener is monitoring.
     */
    public void sheetViewChanged(final Spreadsheet s);
}
