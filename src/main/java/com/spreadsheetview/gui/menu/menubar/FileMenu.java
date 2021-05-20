package com.spreadsheetview.gui.menu.menubar;

public class FileMenu extends Menu {

    public FileMenu() {
        super("File");

        for (FileMenuItems e : FileMenuItems.values()) {
            addMenu(e.getName(), e.getListener());
        }
    }
}
