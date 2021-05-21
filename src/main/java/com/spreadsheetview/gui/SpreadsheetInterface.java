package com.spreadsheetview.gui;

import com.spreadsheetmodel.Spreadsheet;
import com.spreadsheetview.gui.menu.Menu;

import javax.swing.*;
import java.awt.*;

final public class SpreadsheetInterface extends JFrame {

    public SpreadsheetInterface(final Spreadsheet model) {
        super();
        setTitle("Java Tabular Calculator");
        setLayout(new BorderLayout());

        final Menu menu = new Menu();
        final SpreadsheetFrame sf = new SpreadsheetFrame(model);
        // add the listener for FormulaBar
        sf.addListener(new SpreadsheetFrameListener() {
            @java.lang.Override
            public void spreadsheetFrameChanged(Spreadsheet model) {
                pack();
                repaint();
            }
        });

        //add(menu, BorderLayout.NORTH);
        add(sf, BorderLayout.CENTER);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setJMenuBar(menu.getMenuBar());

        getContentPane().add(menu.getToolBar(), BorderLayout.NORTH);

        setPreferredSize(new Dimension(800, 800));
        pack();
    }

    public static void main(String[] args) {
        Spreadsheet v = new Spreadsheet();
        SpreadsheetInterface spi = new SpreadsheetInterface(v);
        spi.setVisible(true);
    }

}
