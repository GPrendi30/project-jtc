package com.spreadsheetview.gui;

import com.spreadsheetmodel.Spreadsheet;
import com.spreadsheetmodel.SpreadsheetListener;
import com.spreadsheetmodel.cell.Cell;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class CellView extends JTextField {

    private static Spreadsheet model;
    private Cell cell;
    private final static Dimension PREFERRED_SIZE = new Dimension(100, 50);
    private final static StringBuilder STRING_BUILDER = new StringBuilder();
    private Dimension size;

    public CellView(Cell c) {
        this.cell = c;
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    String s = getText();

                    System.out.println(s);
                    if (isFocusOwner()) {
                        int x = cell.getLocation().getRow();
                        int y = cell.getLocation().getIntColumn();
                        model.selectCell(x, y);
                        updateCellContent(s);
                    }
                }
            }
        });
        updateContent(c.getText());
        setSize(PREFERRED_SIZE);
        model.addListener(new SpreadsheetListener() {
            @Override
            public void spreadsheetChanged(Spreadsheet s) {
                updateContent(cell.getText());
            }
        });
    }

    public static void setModel(Spreadsheet s) {
        model = s;
    }

    private void updateCellContent(String text) {
        model.updateCurrentCell(text);
        cell.updateContent(text);
        updateContent(cell.getText());
    }

    public void updateContent(String text) {
        setText(text);
    }
    
}
