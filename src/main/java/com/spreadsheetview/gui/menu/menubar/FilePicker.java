package com.spreadsheetview.gui.menu.menubar;


import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * The file picker.
 *
 * @author Di Pietro Enrico, Prendi Gerald.
 *
 */
public class FilePicker extends JFileChooser {

    /**
     * Creates a FilePicker for given extension(extensions).
     * @param desc the description.
     * @param extensions a List of String extensions.
     */
    public FilePicker(final String desc, final String... extensions) {
        super();
        setFileFilter(new FileNameExtensionFilter(desc, extensions));
    }

}
