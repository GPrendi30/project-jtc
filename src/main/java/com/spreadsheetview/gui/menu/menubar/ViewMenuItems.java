package com.spreadsheetview.gui.menu.menubar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public enum ViewMenuItems implements MenuItems {

    FORMULA_BAR("Formula Bar", new ActionListener() {
        @Override
        public void actionPerformed(final ActionEvent actionEvent) {
            System.out.println("Show bar");
        }
    }),
    FORMULAS("Show/Hide formulae", new ActionListener() {
        @Override
        public void actionPerformed(final ActionEvent actionEvent) {
            System.out.println("Show formulae");
        }
    }),

    FULLSCREEN("Fullscreen", new ActionListener() {
        @Override
        public void actionPerformed(final ActionEvent actionEvent) {
            System.out.println("Fullscreen");
        }
    });




    private String name;
    private final ActionListener listener;

    private ViewMenuItems(final String name, final ActionListener listener) {
        this.name = name;
        this.listener = listener;
    }


    private void updateName(final String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public ActionListener getListener() {
        return listener;
    }

}
