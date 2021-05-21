package com.spreadsheetview.gui;

import com.spreadsheetmodel.Spreadsheet;
import com.spreadsheetmodel.SpreadsheetListener;
import com.spreadsheetmodel.cell.Cell;
import com.spreadsheetmodel.sheet.Sheet;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


public class SheetView extends JScrollPane {

    private JPanel mainGrid;
    private Spreadsheet model;
    private GridLayout layout;
    private final ArrayList<SheetViewListener> listeners;

    public SheetView(final Spreadsheet model) {
        this.model = model;
        Sheet current = model.getCurrentSheet();
        listeners = new ArrayList<>();

        layout = new GridLayout(current.sizeX(), current.sizeY());
        mainGrid = new JPanel(layout);

        CellView.setModel(model);
        drawSheet(current);
        model.addListener(new SpreadsheetListener() {
            @Override
            public void spreadsheetChanged(Spreadsheet s) {
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
                Cell c = current.get(x,y);
                CellView cv = new CellView(c);
                mainGrid.add(cv);
            }
        }
    }

    private void removeCells() {

    }

    public static void main(String[] args){
            JFrame frame = new JFrame();
            Spreadsheet m = new Spreadsheet();
            SheetView sv = new SheetView(m);
            frame.add(sv);
            frame.setSize(new Dimension(500, 500));
            frame.pack();
            frame.setVisible(true);
    }
}

