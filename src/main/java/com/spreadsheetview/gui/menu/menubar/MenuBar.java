package com.spreadsheetview.gui.menu.menubar;

import javax.swing.*;

public class MenuBar extends JMenuBar {

    public MenuBar() {
        super();
        JMenu file = new FileMenu();
        JMenu edit = new EditMenu();
        JMenu view = new ViewMenu();

        add(file);
        add(edit);
        add(view);
    }
}
