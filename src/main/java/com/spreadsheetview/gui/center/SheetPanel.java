package com.spreadsheetview.gui.center;

import com.spreadsheetmodel.Spreadsheet;

import java.awt.BorderLayout;

import java.util.ArrayList;
import javax.swing.JPanel;

/**
 * The sheet panel.
 *
 * @author Di Pietro Enrico, Prendi Gerald.
 *
 */
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
            public void sheetViewChanged(final SheetView s) {
                fireSpreadsheetFrameChanged();
            }
        });

        final FormulaBar formulaBar = new FormulaBar(model);
        add(formulaBar, BorderLayout.NORTH);

        formulaBar.addListener(new FormulaBarListener() {
            @Override
            public void formulaBarChanged(final Spreadsheet model) {
                fireSpreadsheetFrameChanged();
            }
        });

        final TabsView tabsBar = new TabsView(model);
        add(tabsBar, BorderLayout.SOUTH);
        tabsBar.addListener(new TabsViewListener() {
            @Override
            public void tabsViewChanged(final Spreadsheet model) {
                fireSpreadsheetFrameChanged();
            }
        });




    }

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
