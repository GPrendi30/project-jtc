package com.spreadsheetview.gui.menu.menubar;

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
                copy();
            }
        });

        addMenu("paste", new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent actionEvent) {
                paste();
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
                cut();
            }
        });
    }

    private static void redo() {
        System.out.println("redo");
    }

    private static void copy() {
        System.out.println("copy");
    }

    private static void paste() {
        System.out.println("paste");
    }

    private static void cut() {
        System.out.println("cut");
    }

    private static void find() {
        System.out.println("find");
    }

    private static void replace() {
        System.out.println("replace");
    }

}
