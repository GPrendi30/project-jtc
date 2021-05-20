package com.spreadsheetview.gui.menu.menubar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class MenuActionListener implements ActionListener {

    public void actionPerformed(ActionEvent actionEvent) {
        command();
    }

    public abstract void command();
}