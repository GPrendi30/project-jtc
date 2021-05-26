package com.spreadsheetview.gui.menu.menubar;

import com.spreadsheetmodel.Spreadsheet;
import com.spreadsheetview.gui.SpreadsheetGui;
import com.spreadsheetview.gui.menu.FilePicker;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;

public enum FileMenuItems implements MenuItems {
    NEW("new",new ActionListener() {
        @Override
        public void actionPerformed(final ActionEvent actionEvent) {
            createNewFile();
            final SpreadsheetGui g = new SpreadsheetGui(new Spreadsheet());
            g.init();
        }

    }),

    OPEN("open",new ActionListener() {
        @Override
        public void actionPerformed(final ActionEvent actionEvent) {
            openFile();
            final FilePicker p = new FilePicker("JTC files", "jtc");
            final int approved = p.showOpenDialog(null);

            if (approved == JFileChooser.APPROVE_OPTION) {
                final File f = p.getSelectedFile();
                System.out.println(f.getName());
            }
        }
    }),

    IMPORT("import", new ActionListener() {
        @Override
        public void actionPerformed(final ActionEvent actionEvent) {
            importFile();
            final FilePicker p = new FilePicker("CSV files", "csv");
            p.setName("Import");
            final int approved = p.showOpenDialog(null);

            if (approved == JFileChooser.APPROVE_OPTION) {
                final File f = p.getSelectedFile();
                System.out.println(f.getName());
            }
        }
    }),

    SAVE("save", new ActionListener() {
        @Override
        public void actionPerformed(final ActionEvent actionEvent) {
            saveFile();
            final FilePicker p = new FilePicker("JTC files", "jtc");
            final int approved = p.showSaveDialog(null);

            if (approved == JFileChooser.APPROVE_OPTION) {
                final File f = p.getSelectedFile();
                System.out.println(f.getName());
            }
        }
    }),

    EXPORT("export", new ActionListener() {
        @Override
        public void actionPerformed(final ActionEvent actionEvent) {
            exportFile();

            final FilePicker p = new FilePicker("CSV files", "csv");
            final int approved = p.showSaveDialog(null);

            if (approved == JFileChooser.APPROVE_OPTION) {
                final File f = p.getSelectedFile();
                System.out.println(f.getName());
            }
        }
    });

    private final String name;
    private final ActionListener listener;

    /**
     * Initialize an EditMenuItem
     * @param name The human-readable name of this TokenType.
     */
    private FileMenuItems(final String name, final ActionListener listener) {
        this.name = name;
        this.listener = listener;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public ActionListener getListener() {
        return listener;
    }


    private static void createNewFile() {
        System.out.println("File created");
    }

    private static void importFile() {
        System.out.println("File imported");
    }

    private static void exportFile() {
        System.out.println("File exported");
    }

    private static void openFile() {
        System.out.println("File opened");
    }

    private static void saveFile() {
        System.out.println("File saved");
    }

}
