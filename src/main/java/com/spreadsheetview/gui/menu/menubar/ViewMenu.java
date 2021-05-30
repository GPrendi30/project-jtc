package com.spreadsheetview.gui.menu.menubar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewMenu extends Menu {

    /**
     * Makes the view of the menu.
     */
    public ViewMenu() {
        super("View");

        addMenu("Formula Bar", new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent actionEvent) {
                System.out.println("Show bar");
            }
        });

        addMenu("Show/Hide formulae", new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent actionEvent) {
                System.out.println("Show formulae");
            }
        });

        addMenu("Fullscreen", new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent actionEvent) {
                System.out.println("Fullscreen");
            }
        });
    }


}
