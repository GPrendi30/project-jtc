package com.spreadsheetview.gui.menu.menubar;

public class EditMenu extends Menu {

    /**
     * Edit the menu.
     */
    public EditMenu() {
        super("edit");

        for (EditMenuItems e : EditMenuItems.values()) {
            addMenu(e.getName(), e.getListener());
        }
    }

}
