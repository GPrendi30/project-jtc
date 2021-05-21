package com.spreadsheetview.gui;

import com.spreadsheetmodel.*;

public interface TabsViewListener {

    /**
     * Listener notified that the tabsView's state has changed.
     * @param s a Spreadsheet that the Listener is monitoring.
     */
    public void tabsViewChanged(final Spreadsheet s);
}
