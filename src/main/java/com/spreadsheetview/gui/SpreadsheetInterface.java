package com.spreadsheetview.gui;

import com.spreadsheetmodel.Spreadsheet;
import com.spreadsheetview.gui.menu.menubar.MenuBar;

import javax.swing.*;
import java.awt.*;

final public class SpreadsheetInterface extends JFrame {

    public SpreadsheetInterface(final Spreadsheet model) {
        super();
        setTitle("Java Tabular Calculator");
        setLayout(new BorderLayout());

        final JMenuBar menu = new MenuBar();
        final JPanel grid = new JPanel(new GridLayout());
        final JPanel sheetMenu = new JPanel(new FlowLayout());

        grid.add(new JButton("Hello from the grid"));
        sheetMenu.add(new JButton("hello from the sheets"));

        add(menu, BorderLayout.NORTH);
        add(grid, BorderLayout.CENTER);
        add(sheetMenu, BorderLayout.SOUTH);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setJMenuBar(menu);
        pack();
    }

    public static void main(String[] args) {
        Spreadsheet v = new Spreadsheet();
        SpreadsheetInterface spi = new SpreadsheetInterface(v);
        spi.setVisible(true);
    }

}
