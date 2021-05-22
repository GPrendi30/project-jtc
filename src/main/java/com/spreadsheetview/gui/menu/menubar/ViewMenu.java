package com.spreadsheetview.gui.menu.menubar;

public class ViewMenu extends Menu {

    /**
     * Makes the view of the menu.
     */
    public ViewMenu() {
        super("View");

        for (final ViewMenuItems e : ViewMenuItems.values()) {
            addMenu(e.getName(), e.getListener());
        }
    }
}
