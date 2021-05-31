package com.spreadsheetview.gui.menu.toolbar;

import com.spreadsheetmodel.commands.Command;
import com.spreadsheetmodel.commands.Invoker;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public abstract class ToolBarActionListener implements ActionListener {

    /**
     * Listener.
     * @param actionEvent an ActionEvent.
     */
    public void actionPerformed(final ActionEvent actionEvent) {
        Invoker.getInstance().invoke(command());
    }

    /**
     * The command.
     * @return returns a Command.
     */
    public abstract Command command();

}
