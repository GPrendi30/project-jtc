package com.spreadsheetview.gui;

import com.spreadsheetmodel.cell.Cell;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class CellView extends JTextField {

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
                String s = getText();
                updateCellContent(s);
            }
        });

        setSize(PREFERRED_SIZE);
    }

    private void updateCellContent(String text) {
        cell.updateContent(text);
        updateContent();
    }

    public void updateContent() {
        setText(cell.getText());
    }
    
}
