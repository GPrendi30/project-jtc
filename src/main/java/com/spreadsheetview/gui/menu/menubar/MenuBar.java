package com.spreadsheetview.gui.menu.menubar;

import com.spreadsheetview.SpreadsheetView;

import javax.swing.JMenu;
import javax.swing.JMenuBar;

/**
 * The menu bar.
 *
 * @author Di Pietro Enrico, Prendi Gerald.
 *
 */
public class MenuBar extends JMenuBar {

    /**
     * Create a menuBar.
     * @param topFrame the topFrame of the menu.
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
