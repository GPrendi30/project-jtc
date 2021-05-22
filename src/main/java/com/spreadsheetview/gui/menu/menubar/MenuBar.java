package com.spreadsheetview.gui.menu.menubar;

import javax.swing.JMenu;
import javax.swing.JMenuBar;

public class MenuBar extends JMenuBar {

    /**
     * Create a menuBar.
     */
    public MenuBar() {
        super();
        final JMenu file = new FileMenu();
        final JMenu edit = new EditMenu();
        final JMenu view = new ViewMenu();

        add(file);
        add(edit);
        add(view);
    }
}
