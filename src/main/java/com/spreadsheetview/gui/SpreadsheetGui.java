package com.spreadsheetview.gui;

import com.spreadsheetmodel.Spreadsheet;
import com.spreadsheetview.SpreadsheetView;
import com.spreadsheetview.gui.center.SheetPanel;
import com.spreadsheetview.gui.center.SheetPanelListener;
import com.spreadsheetview.gui.menu.Menu;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JFrame;


public final class SpreadsheetGui extends JFrame implements SpreadsheetView {

    public static final int DEFAULT_X = 1000;
    public static final int DEFAULT_Y = 800;

    /**
     * Create a spreadsheetInterface.
     * @param model a Spreadsheet.
     */
    public SpreadsheetGui(final Spreadsheet model) {
        super();
        setTitle("Java Tabular Calculator");
        setLayout(new BorderLayout());


        final Menu menu = new Menu(this);
        final SheetPanel sf = new SheetPanel(model);
        // add the listener for FormulaBar
        sf.addListener(new SheetPanelListener() {
            @Override
            public void spreadsheetFrameChanged(final Spreadsheet model) {
                sf.repaint();
                repaint();
                pack();
            }
        });

        //add(menu, BorderLayout.NORTH);
        add(sf, BorderLayout.CENTER);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        setJMenuBar(menu.getMenuBar());
        getContentPane().add(menu.getToolBar(), BorderLayout.NORTH);

        setPreferredSize(new Dimension(DEFAULT_X, DEFAULT_Y));
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

    @Override
    public void updateModel(final Spreadsheet newModel) {
        final SpreadsheetGui gui = new SpreadsheetGui(newModel);
        gui.init();
    }

    /**
     * The main method.
     * @param args a String[].
     */
    public static void main(final String[] args) {
        final Spreadsheet m = new Spreadsheet(5,5);
        final SpreadsheetGui sv = new SpreadsheetGui(m);
        sv.init();
    }


}
