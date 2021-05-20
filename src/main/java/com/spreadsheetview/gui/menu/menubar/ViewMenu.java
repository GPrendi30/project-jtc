package com.spreadsheetview.gui.menu.menubar;

public class ViewMenu extends Menu {

    public ViewMenu() {
        super("View");

        for (ViewMenuItems e : ViewMenuItems.values()) {
            addMenu(e.getName(), e.getListener());
        }
    }
}
