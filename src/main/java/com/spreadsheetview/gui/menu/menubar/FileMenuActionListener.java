package com.spreadsheetview.gui.menu.menubar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;

/**
 * The file menu action listener.
 *
 * @author Di Pietro Enrico, Prendi Gerald.
 *
 */
public abstract class FileMenuActionListener implements ActionListener {

    public static final int OPEN_DIALOG = 1;
    public static final  int SAVE_DIALOG = 2;
    private final FilePicker picker;
    private final int dialogType;

    /**
     * Creates a fileMenuActionListener based on the extension, and the dialog type.
     * @param generalFileNames the General Name of the files to accept, i.e "CVS FILES"
     * @param extension an extension to filter files.
     * @param dialogType the dialog type, OPEN_DIALOG, SAVE_DIALOG.
     */
    public FileMenuActionListener(final String generalFileNames,
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
     * @param path a path for FileMenuItems.
     */
    public abstract void command(final String path);
}
