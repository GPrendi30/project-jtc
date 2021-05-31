package com.spreadsheetview.gui.menu.menubar;

import com.spreadsheetcontroller.SpreadsheetController;
import com.spreadsheetmodel.commands.ExportCommand;
import com.spreadsheetmodel.commands.ImportCommand;
import com.spreadsheetmodel.commands.Invoker;
import com.spreadsheetmodel.commands.OpenCommand;
import com.spreadsheetmodel.commands.SaveCommand;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



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
                SpreadsheetController.main(new String[]{"gui"});
            }
        });

        // Add the menu "open"
        addMenu("open", new FileMenuActionListener(
                "JTC Files",
                "jtc",
                FileMenuActionListener.OPEN_DIALOG) {
            @Override
            public void command(String path) {
                final OpenCommand openCommand = new OpenCommand(path);
                Invoker.getInstance().invoke(openCommand);
            }
        });

        // Add the menu "import"
        addMenu("import", new FileMenuActionListener(
                "CSV files",
                "csv",
                FileMenuActionListener.OPEN_DIALOG) {
            @Override
            public void command(String path) {
                final ImportCommand importCommand = new ImportCommand(path);
                Invoker.getInstance().invoke(importCommand);
            }
        });

        // Add the menu "save"
        addMenu("save", new FileMenuActionListener(
                "JTC Files",
                "jtc",
                FileMenuActionListener.SAVE_DIALOG) {
            @Override
            public void command(String path) {
                final SaveCommand saveCommand = new SaveCommand(path);
                Invoker.getInstance().invoke(saveCommand);
            }
        });

        // Add the menu "export"
        addMenu("export",new FileMenuActionListener(
                "CSV Files",
                "csv",
                FileMenuActionListener.SAVE_DIALOG) {
            @Override
            public void command(String path) {
                final ExportCommand exportCommand = new ExportCommand(path);
                Invoker.getInstance().invoke(exportCommand);
            }
        });

    }
}
