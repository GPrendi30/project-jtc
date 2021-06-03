package com.spreadsheetview.gui.center;

import com.spreadsheetmodel.Spreadsheet;

import java.io.Serializable;

/**
 * The sheet panel listener.
 *
 * @author Di Pietro Enrico, Prendi Gerald.
 *
 */
public interface SheetPanelListener extends Serializable {

    /**
     * Listener notified that the spreadsheetFrame's state has changed.
     * @param s a Spreadsheet that the Listener is monitoring.
     */
    public void spreadsheetFrameChanged(final Spreadsheet s);
}