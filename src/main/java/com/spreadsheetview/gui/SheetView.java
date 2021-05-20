package com.spreadsheetview.gui;

import com.spreadsheetmodel.Spreadsheet;
import com.spreadsheetmodel.cell.Cell;
import com.spreadsheetmodel.sheet.Sheet;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class SheetView extends JScrollPane {

    private Spreadsheet model;

    public SheetView(final Spreadsheet model) {
        this.model = model;
        Sheet current = model.getCurrentSheet();
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

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        Spreadsheet m = new Spreadsheet();
        SheetView sv = new SheetView(m);
        frame.add(sv);
        frame.setSize(new Dimension(500, 500));
        frame.pack();
        frame.setVisible(true);
    }


}
