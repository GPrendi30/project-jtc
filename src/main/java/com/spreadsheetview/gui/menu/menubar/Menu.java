package com.spreadsheetview.gui.menu.menubar;

import javax.swing.*;
import java.awt.event.ActionListener;

public abstract class Menu extends JMenu {

    public Menu(String name) {
        super(name);
    }

    public void addMenu(String name, ActionListener actionListener) {
        JMenuItem temp = new JMenuItem(name);
        temp.addActionListener(actionListener);
        add(temp);
    };

}
