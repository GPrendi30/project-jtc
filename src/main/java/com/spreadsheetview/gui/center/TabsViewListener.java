package com.spreadsheetview.gui.center;

import com.spreadsheetmodel.Spreadsheet;

/**
 * The tabs view listener.
 *
 * @author Di Pietro Enrico, Prendi Gerald.
 *
 */
public interface TabsViewListener {

    /**
     * Listener notified that the tabsView's state has changed.
     * @param s a Spreadsheet that the Listener is monitoring.
     */
    public void tabsViewChanged(final Spreadsheet s);
}
