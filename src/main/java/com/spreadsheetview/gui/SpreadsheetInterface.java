package com.spreadsheetview.gui;

import com.spreadsheetmodel.Spreadsheet;
import com.spreadsheetview.SpreadsheetView;
import com.spreadsheetview.gui.menu.Menu;
import com.spreadsheetview.tui.SpreadsheetTui;

import javax.swing.*;
import java.awt.*;

final public class SpreadsheetInterface extends JFrame implements SpreadsheetView {

    public SpreadsheetInterface(final Spreadsheet model) {
        super();
        setTitle("Java Tabular Calculator");
        setLayout(new BorderLayout());

        final SpreadsheetTui t = new SpreadsheetTui(model);
        final Menu menu = new Menu();
        final SheetPanel sf = new SheetPanel(model);
        // add the listener for FormulaBar
        sf.addListener(new SheetPanelListener() {
            @Override
            public void spreadsheetFrameChanged(Spreadsheet model) {
                pack();
                repaint();
                t.printSheet(model);
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

    @Override
    public void init() {
        setVisible(true);
    }

    @Override
    public void updateView() {
        // empty
    }
}
