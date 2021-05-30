package com.spreadsheetview.gui.menu.menubar;

import com.spreadsheetcontroller.SpreadsheetController;
import com.spreadsheetmodel.commands.ImportCommand;
import com.spreadsheetmodel.commands.Invoker;
import com.spreadsheetview.gui.menu.FilePicker;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;


public class FileMenu extends Menu {

    /**
     * Edit the menu.
     */
    public FileMenu() {
        super("File");

        // Add the menu "new"
        addMenu("new", new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent actionEvent) {
                createNewFile();
                SpreadsheetController.main(new String[]{"gui"});
            }
        });

        // Add the menu "open"
        addMenu("open",new ActionListener() {
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
        });

        // Add the menu "import"
        addMenu("import", new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent actionEvent) {
                importFile();
                final FilePicker p = new FilePicker("CSV files", "csv");
                p.setName("Import");
                final int approved = p.showOpenDialog(null);

                if (approved == JFileChooser.APPROVE_OPTION) {
                    final File f = p.getSelectedFile();
                    final ImportCommand importCommand = new ImportCommand();
                    importCommand.setPath(f.getPath().toString());
                    Invoker.getInstance().invoke(importCommand);
                    System.out.println(f.getName());
                }
            }
        });

        // Add the menu "save"
        addMenu("save", new ActionListener() {
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
        });

        // Add the menu "export"
        addMenu("export", new ActionListener() {
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
