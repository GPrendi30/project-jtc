package com.spreadsheetview.gui.menu.menubar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public enum EditMenuItems {
    UNDO("undo", new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            undo();
        }
    }),

    REDO("redo", new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            redo();
        }
    }),

    COPY("copy", new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            copy();
        }
    }),

    PASTE("paste", new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            paste();
        }
    }),

    FIND("find", new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            find();
        }
    }),
    REPLACE("replace", new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            replace();
        }
    }),

    CUT("cut", new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            cut();
        }
    });


    private final String name;
    private final ActionListener listener;

    /**
     * Initialize an EditMenuItem
     * @param name The human-readable name of this TokenType.
     */
    private EditMenuItems(final String name, ActionListener listener) {
        this.name = name;
        this.listener = listener;
    }

    /**
     * Get the human-readable name.
     * @return the name of this EditMenuItem.
     */
    public String getName() {
        return name;
    }

    /**
     * Get the listener of the EditMenuItem.
     * @return the listener of this EditMenuItem.
     */
    public ActionListener getListener() {return listener;}


    private static void undo() {
        System.out.println("undo");
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
