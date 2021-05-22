package com.spreadsheetview.gui;

import com.spreadsheetmodel.Spreadsheet;
import com.spreadsheetmodel.SpreadsheetListener;
import com.spreadsheetmodel.cell.Cell;
import com.spreadsheetmodel.sheet.Sheet;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;


public class SheetView extends JScrollPane {

    private JPanel mainGrid;
    private Spreadsheet model;
    private final ArrayList<SheetViewListener> listeners;

    /**
     * Create a sheetView.
     * @param model a Spreadsheet.
     */
    public SheetView(final Spreadsheet model) {
        super();
        this.model = model;
        final Sheet current = model.getCurrentSheet();
        listeners = new ArrayList<>();

        final GridLayout layout = new GridLayout(current.sizeX(), current.sizeY());
        mainGrid = new JPanel(layout);

        CellView.setModel(model);
        drawSheet(current);
        model.addListener(new SpreadsheetListener() {
            @Override
            public void spreadsheetChanged(final Spreadsheet s) {
                fireSheetViewChanged();
                //TODO add a type to the Spreadsheetchanged() method.
                //drawSheet(model.getCurrentSheet());
            }
        });
        setViewportView(mainGrid);
    }

    // adding listeners
    /**
     * Adds a SheetViewListener to the list of listener objects.
     * @param li a SheetViewListener that will be added.
     */
    public void addListener(final SheetViewListener li) {
        listeners.add(li);
    }

    /**
     * Removes a listener from the list of listeners.
     * @param li a Spreadsheet listener that will be removed.
     */
    public void removeListener(final SheetViewListener li) {
        listeners.remove(li);
    }

    private void fireSheetViewChanged() {
        for (final SheetViewListener li : listeners) {
            li.sheetViewChanged(model);
        }
    }

    private void drawSheet(final Sheet current) {
        mainGrid.setLayout(new GridLayout(current.sizeX(), current.sizeY()));
        for (int x = 0; x < current.sizeX(); x++) {
            for (int y = 0; y < current.sizeY(); y++) {
                final Cell c = current.get(x,y);
                final CellView cv = new CellView(c);
                mainGrid.add(cv);
            }
        }
    }

    /**
     * The main method.
     * @param args a String[].
     */
    public static void main(final String[] args) {
        final JFrame frame = new JFrame();
        final Spreadsheet m = new Spreadsheet();
        final SheetView sv = new SheetView(m);
        frame.add(sv);
        frame.setSize(new Dimension(500, 500));
        frame.pack();
        frame.setVisible(true);
    }
}

