package com.spreadsheetview.gui.menu.menubar;

import com.spreadsheetview.gui.FileMenuItems;

public class FileMenu extends Menu {

    public FileMenu() {
        super("File");

        for (FileMenuItems e : FileMenuItems.values()) {
            addMenu(e.getName(), e.getListener());
        }
    }
}
