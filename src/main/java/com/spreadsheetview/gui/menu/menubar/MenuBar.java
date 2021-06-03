package com.spreadsheetview.gui.menu.menubar;

import com.spreadsheetview.SpreadsheetView;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

public class MenuBar extends JMenuBar {

    /**
     * Create a menuBar.
     */
    public MenuBar(final SpreadsheetView topFrame) {
        super();
        final JMenu file = new FileMenu(topFrame);
        final JMenu edit = new EditMenu();
        final JMenu view = new ViewMenu();

        add(file);
        add(edit);
        add(view);
    }
}
