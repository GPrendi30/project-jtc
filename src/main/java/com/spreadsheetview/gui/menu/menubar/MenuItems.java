package com.spreadsheetview.gui.menu.menubar;

import java.awt.event.ActionListener;

public interface MenuItems {

    /**
     * Get the human-readable name.
     * @return the name of this EditMenuItem.
     */
    public String getName();

    /**
     * Get the listener of the EditMenuItem.
     * @return the listener of this EditMenuItem.
     */
    public ActionListener getListener();

}
