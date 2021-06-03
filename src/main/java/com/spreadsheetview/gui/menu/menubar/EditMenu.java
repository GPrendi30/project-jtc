package com.spreadsheetview.gui.menu.menubar;

import com.spreadsheetmodel.commands.CopyCommand;
import com.spreadsheetmodel.commands.CopyPasteStack;
import com.spreadsheetmodel.commands.CutCommand;
import com.spreadsheetmodel.commands.PasteCommand;
import com.spreadsheetview.gui.GuiCommandHandler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditMenu extends Menu {

    /**
     * Edit the menu.
     */
    public EditMenu() {
        super("edit");

        addMenu("undo", new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent actionEvent) {
                // fix undo command.
            }
        });

        addMenu("redo", new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent actionEvent) {
                redo();
            }
        });

        addMenu("copy", new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent actionEvent) {
                GuiCommandHandler.handleCommand(new CopyCommand(CopyPasteStack.getInstance()));
            }
        });

        addMenu("paste", new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent actionEvent) {
                GuiCommandHandler.handleCommand(new PasteCommand(CopyPasteStack.getInstance()));
            }
        });

        addMenu("find", new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent actionEvent) {
                find();
            }
        });

        addMenu("replace", new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent actionEvent) {
                replace();
            }
        });

        addMenu("cut", new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent actionEvent) {
                GuiCommandHandler.handleCommand(new CutCommand(CopyPasteStack.getInstance()));
            }
        });
    }

    private static void redo() {
        System.out.println("redo");
    }

    private static void find() {
        System.out.println("find");
    }

    private static void replace() {
        System.out.println("replace");
    }

}
