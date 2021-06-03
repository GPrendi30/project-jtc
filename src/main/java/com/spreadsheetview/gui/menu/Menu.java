package com.spreadsheetview.gui.menu;

import com.spreadsheetview.SpreadsheetView;
import com.spreadsheetview.gui.menu.menubar.MenuBar;
import com.spreadsheetview.gui.menu.toolbar.ToolBar;

/**
 * The menu.
 *
 * @author Di Pietro Enrico, Prendi Gerald.
 *
 */
public class Menu {

    private final MenuBar menuBar;
    private final ToolBar toolBar;

    /**
     * Create a menu.
     * @param parent the parent of the Menu.
     */
    public Menu(final SpreadsheetView parent) {

        menuBar = new MenuBar(parent);
        toolBar = new ToolBar();
    }

    /**
     * Gets the menu bar.
     * @return MenuBar the menuBar.
     */
    public MenuBar getMenuBar() {
        return menuBar;
    }

    /**
     * Gets the tool bar.
     * @return ToolBar the toolBar.
     */
    public ToolBar getToolBar() {
        return toolBar;
    }

}
