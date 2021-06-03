package com.spreadsheetview.gui.center;

/**
 * The sheet view listener.
 *
 * @author Di Pietro Enrico, Prendi Gerald.
 *
 */
public interface SheetViewListener {

    /**
     * Listener notified that the sheetView's state has changed.
     * @param s a Spreadsheet that the Listener is monitoring.
     */
    public void sheetViewChanged(final SheetView s);
}
