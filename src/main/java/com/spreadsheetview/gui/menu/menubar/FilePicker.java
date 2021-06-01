package com.spreadsheetview.gui.menu;


import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

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
