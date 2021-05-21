package com.spreadsheetview.gui;

import com.spreadsheetmodel.Spreadsheet;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


public class SheetPanel extends JPanel {

    private Spreadsheet model;
    private GridLayout grid;
    private FormulaBar formulaBar;
    private TabsView tabsBar;
    private final ArrayList<SheetPanelListener> listeners;
    
    public SheetPanel(Spreadsheet model) {
        super();
        this.model = model;
        listeners = new ArrayList<>();

        setLayout(new BorderLayout());
        SheetView sv = new SheetView(model);
        add(sv, BorderLayout.CENTER);
        // add the listener for SheetView
        sv.addListener(new SheetViewListener() {
            @java.lang.Override
            public void sheetViewChanged(Spreadsheet model) {
                fireSpreadsheetFrameChanged();
            }
        });

        formulaBar = new FormulaBar(model);
        add(formulaBar, BorderLayout.NORTH);
        // add the listener for FormulaBar
        formulaBar.addListener(new FormulaBarListener() {
            @java.lang.Override
            public void formulaBarChanged(Spreadsheet model) {
                fireSpreadsheetFrameChanged();
            }
        });

        tabsBar = new TabsView(model);
        add(tabsBar, BorderLayout.SOUTH);
        // add the listener for TabsView
        tabsBar.addListener(new TabsViewListener() {
            @java.lang.Override
            public void tabsViewChanged(Spreadsheet model) {
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
}
