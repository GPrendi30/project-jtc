package com.spreadsheetview.gui;

import com.spreadsheetmodel.Spreadsheet;
import com.spreadsheetmodel.cell.Cell;
import com.spreadsheetmodel.sheet.Sheet;
import com.spreadsheetmodel.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

import java.util.ArrayList;


public class SheetView extends JScrollPane {

    private Spreadsheet model;
    private final ArrayList<SheetViewListener> listeners;

    public SheetView(final Spreadsheet model) {
        this.model = model;
        Sheet current = model.getCurrentSheet();
        listeners = new ArrayList<>();

        JPanel j = new JPanel();
        j.setLayout(new GridLayout(current.sizeX(), current.sizeY()));
        Sheet[] sh = model.getSheets();

        for (int x = 0; x < current.sizeX(); x++) {
            for (int y = 0; y < current.sizeY(); y++) {
                Cell c = current.get(x,y);
                CellView cv = new CellView(c);
                j.add(cv);
            }
        }
        setViewportView(j);
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

