package com.spreadsheetview.gui.menu.menubar;

import com.spreadsheetmodel.commands.Invoker;
import com.spreadsheetmodel.commands.OpenCommand;
import com.spreadsheetview.gui.menu.FilePicker;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Locale;
import javax.swing.*;

public abstract class MenuActionListener implements ActionListener {

    public final static int OPEN_DIALOG = 1;
    public final static int SAVE_DIALOG = 2;
    private final FilePicker picker;
    private final int dialogType;

    public MenuActionListener(final String generalFileNames,
                              final String extension,
                              final int dialogType) {
        this.dialogType = dialogType;
        this.picker = new FilePicker(generalFileNames, extension);

    }
    /**
     * Listener.
     * @param actionEvent an ActionEvent.
     */
    public void actionPerformed(final ActionEvent actionEvent) {
        final int approved = dialogType == OPEN_DIALOG
                ? picker.showOpenDialog(null)
                : picker.showSaveDialog(null);

        if (approved == JFileChooser.APPROVE_OPTION) {
            final File f = picker.getSelectedFile();
            command(f.getPath().toString());
        }
    }

    /**
     * The command.
     */
    public abstract void command(final String path);
}
