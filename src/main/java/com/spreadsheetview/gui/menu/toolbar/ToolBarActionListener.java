package com.spreadsheetview.gui.menu.toolbar;

import com.spreadsheetmodel.commands.Command;
import com.spreadsheetmodel.commands.Invoker;
import com.spreadsheetview.gui.menu.FilePicker;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.*;

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
     */
    public abstract Command command();

}
