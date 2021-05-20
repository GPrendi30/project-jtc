package com.spreadsheetview.gui.menu;

import com.spreadsheetview.gui.menu.menubar.MenuBar;
import com.spreadsheetview.gui.menu.toolbar.ToolBar;

public class Menu {

    private final MenuBar menuBar;
    private final ToolBar toolBar;
    public Menu() {

        menuBar = new MenuBar();
        toolBar = new ToolBar();
    }

    public MenuBar getMenuBar() {
        return menuBar;
    }

    public ToolBar getToolBar() {
        return toolBar;
    }

}
