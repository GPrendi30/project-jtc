package com.spreadsheetview.gui;

import com.spreadsheetmodel.Spreadsheet;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class SheetPanel extends JPanel {

    private Spreadsheet model;
    private final ArrayList<SheetPanelListener> listeners;

    /**
     * Create a sheetPanel.
     * @param model a Spreadsheet.
     */
    public SheetPanel(final Spreadsheet model) {
        super();
        this.model = model;
        listeners = new ArrayList<>();

        setLayout(new BorderLayout());
        final SheetView sv = new SheetView(model);
        add(sv, BorderLayout.CENTER);
        // add the listener for SheetView
        sv.addListener(new SheetViewListener() {
            @Override
            public void sheetViewChanged(final Spreadsheet model) {
                fireSpreadsheetFrameChanged();
            }
        });

        final FormulaBar formulaBar = new FormulaBar(model);
        add(formulaBar, BorderLayout.NORTH);
        // add the listener for FormulaBar
        formulaBar.addListener(new FormulaBarListener() {
            @Override
            public void formulaBarChanged(final Spreadsheet model) {
                fireSpreadsheetFrameChanged();
            }
        });

        final TabsView tabsBar = new TabsView(model);
        add(tabsBar, BorderLayout.SOUTH);
        // add the listener for TabsView
        tabsBar.addListener(new TabsViewListener() {
            @Override
            public void tabsViewChanged(final Spreadsheet model) {
                fireSpreadsheetFrameChanged();
            }
        });


    }

    // adding listeners
    /**
     * Adds a SheetViewListener to the list of listener objects.
     * @param li a SheetViewListener that will be added.
     */
    public void addListener(final SheetPanelListener li) {
        listeners.add(li);
    }

    /**
     * Removes a listener from the list of listeners.
     * @param li a Spreadsheet listener that will be removed.
     */
    public void removeListener(final SheetPanelListener li) {
        listeners.remove(li);
    }

    private void fireSpreadsheetFrameChanged() {
        for (final SheetPanelListener li : listeners) {
            li.spreadsheetFrameChanged(model);
        }
    }

    /**
     * The main method.
     * @param args a String[].
     */
    public static void main(final String[] args) {
        final JFrame frame = new JFrame();
        final Spreadsheet m = new Spreadsheet();
        final SheetPanel sv = new SheetPanel(m);
        frame.add(sv);
        frame.setSize(new Dimension(500, 500));
        frame.pack();
        frame.setVisible(true);
    }
}
