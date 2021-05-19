package com.spreadsheetview.gui.menu.menubar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class MenuActionListener implements ActionListener {

    public void actionPerformed(ActionEvent actionEvent) {
        performAction();
    }

    public abstract void performAction();
}
