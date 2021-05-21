package com.spreadsheetview.gui.menu.menubar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class MenuActionListener implements ActionListener {

    /**
     * Listener.
     * @param actionEvent an ActionEvent.
     */
    public void actionPerformed(ActionEvent actionEvent) {
        command();
    }

    /**
     * The command.
     */
    public abstract void command();
}
