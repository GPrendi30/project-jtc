package com.spreadsheetview.gui.menu;

import com.spreadsheetview.SpreadsheetView;
import com.spreadsheetview.gui.menu.menubar.MenuBar;
import com.spreadsheetview.gui.menu.toolbar.ToolBar;

public class Menu {

    private final MenuBar menuBar;
    private final ToolBar toolBar;

    /**
     * Create a menu.
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
