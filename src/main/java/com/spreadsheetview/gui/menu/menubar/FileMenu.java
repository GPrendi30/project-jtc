package com.spreadsheetview.gui.menu.menubar;

import com.spreadsheetcontroller.SpreadsheetController;
import com.spreadsheetmodel.SpreadsheetException;
import com.spreadsheetmodel.commands.ExportCommand;
import com.spreadsheetmodel.commands.ImportCommand;
import com.spreadsheetmodel.commands.OpenCommand;
import com.spreadsheetmodel.commands.SaveCommand;
import com.spreadsheetview.SpreadsheetView;
import com.spreadsheetview.gui.GuiCommandHandler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class FileMenu extends Menu {

    /**
     * Edit the menu.
     * @param view a SpreadsheetView
     */
    public FileMenu(final SpreadsheetView view) {
        super("File");

        // Add the menu "new"
        addMenu("new", new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent actionEvent) {
                try {
                    SpreadsheetController.main(new String[]{"gui"});
                } catch (SpreadsheetException exception) {
                    exception.printStackTrace();
                }
            }
        });

        // Add the menu "open"
        addMenu("open", new FileMenuActionListener(
                "JTC Files",
                "jtc",
                FileMenuActionListener.OPEN_DIALOG) {
            @Override
            public void command(final String path) {
                GuiCommandHandler.handleCommand(new OpenCommand(path, view));
            }
        });


        addMenu("import", new FileMenuActionListener(
                "CSV files",
                "csv",
                FileMenuActionListener.OPEN_DIALOG) {
            @Override
            public void command(final String path) {
                GuiCommandHandler.handleCommand(new ImportCommand(path));
            }
        });

        // Add the menu "save"
        addMenu("save", new FileMenuActionListener(
                "JTC Files",
                "jtc",
                FileMenuActionListener.SAVE_DIALOG) {
            @Override
            public void command(final String path) {
                GuiCommandHandler.handleCommand(new SaveCommand(path));
            }
        });

        // Add the menu "export"
        addMenu("export",new FileMenuActionListener(
                "CSV Files",
                "csv",
                FileMenuActionListener.SAVE_DIALOG) {
            @Override
            public void command(final String path) {
                GuiCommandHandler.handleCommand(new ExportCommand(path));
            }
        });

    }
}
