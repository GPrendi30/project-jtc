package com.spreadsheetview.gui.menu.menubar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public enum FileMenuItems implements MenuItems {
    NEW("new",new ActionListener() {
        @Override
        public void actionPerformed(final ActionEvent actionEvent) {
            createNewFile();
        }
    }),

    OPEN("open",new ActionListener() {
        @Override
        public void actionPerformed(final ActionEvent actionEvent) {
            openFile();
        }
    }),

    IMPORT("import", new ActionListener() {
        @Override
        public void actionPerformed(final ActionEvent actionEvent) {
            importFile();
        }
    }),

    SAVE("save", new ActionListener() {
        @Override
        public void actionPerformed(final ActionEvent actionEvent) {
            saveFile();
        }
    }),

    EXPORT("export", new ActionListener() {
        @Override
        public void actionPerformed(final ActionEvent actionEvent) {
            exportFile();
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
