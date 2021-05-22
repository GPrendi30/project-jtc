package com.spreadsheetview.gui.menu.menubar;

public class FileMenu extends Menu {

    /**
     * Edit the menu.
     */
    public FileMenu() {
        super("File");

        for (final FileMenuItems e : FileMenuItems.values()) {
            addMenu(e.getName(), e.getListener());
        }
    }
}
