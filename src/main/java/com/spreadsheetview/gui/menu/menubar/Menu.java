package com.spreadsheetview.gui.menu.menubar;


import java.awt.event.ActionListener;
import javax.swing.*;

public abstract class Menu extends JMenu {

    /**
     * Create a menu.
     * @param name a String.
     */
    public Menu(String name) {
        super(name);
    }

    /**
     * Add the menu.
     * @param name a String.
     * @param actionListener an ActionListener.
     */
    public void addMenu(String name, ActionListener actionListener) {
        JMenuItem temp = new JMenuItem(name);
        temp.addActionListener(actionListener);
        add(temp);
    }

}
