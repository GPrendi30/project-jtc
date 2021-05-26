package com.spreadsheetview.gui;

import com.spreadsheetmodel.Spreadsheet;
import com.spreadsheetview.SpreadsheetView;
import com.spreadsheetview.gui.menu.Menu;

import java.awt.*;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import javax.swing.*;


public final class SpreadsheetGui extends JFrame implements SpreadsheetView {

    /**
     * Create a spreadsheetInterface.
     * @param model a Spreadsheet.
     */
    public SpreadsheetGui(final Spreadsheet model) {
        super();
        setTitle("Java Tabular Calculator");
        setLayout(new BorderLayout());


        final Menu menu = new Menu();
        final SheetPanel sf = new SheetPanel(model);
        // add the listener for FormulaBar
        sf.addListener(new SheetPanelListener() {
            @Override
            public void spreadsheetFrameChanged(final Spreadsheet model) {
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

    @Override
    public void init() {
        setVisible(true);
    }

    @Override
    public void updateView() {
        // empty
    }




}
