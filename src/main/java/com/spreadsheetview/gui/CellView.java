package com.spreadsheetview.gui;

import com.spreadsheetmodel.Spreadsheet;
import com.spreadsheetmodel.SpreadsheetEvent;
import com.spreadsheetmodel.SpreadsheetListener;
import com.spreadsheetmodel.cell.Cell;

import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JTextField;


public class CellView extends JTextField {

    private static Spreadsheet model;
    private Cell cell;
    private final Dimension preferredSize = new Dimension(100, 50);
    // eliminate those two?? v
    //private final StringBuilder stringBuilder = new StringBuilder();
    //private Dimension size;

    /**
     * Create a CellView object.
     * @param c a cell.
     */
    public CellView(final Cell c) {
        super();
        this.cell = c;
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(final KeyEvent e) {
                super.keyPressed(e);
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    final String s = getText();

                    System.out.println(s);
                    if (isFocusOwner()) {
                        final int x = cell.getLocation().getRow();
                        final int y = cell.getLocation().getIntColumn();
                        model.selectCell(x, y);
                        updateCellContent(s);
                    }
                }
            }
        });
        updateContent(c.getText());
        setSize(preferredSize);
        model.addListener(new SpreadsheetListener() {
            @Override
            public void spreadsheetChanged(Spreadsheet s, SpreadsheetEvent se) {
                updateContent(cell.getText());
            }

        });
    }

    /**
     * Set the model to the current sheet.
     * @param s the spreadsheet.
     */
    public static void setModel(final Spreadsheet s) {
        model = s;
    }

    private void updateCellContent(final String text) {
        model.updateCurrentCell(text);
        cell.updateContent(text);
        updateContent(cell.getText());
    }

    /**
     * Updates the content.
     * @param text a String.
     */
    public void updateContent(final String text) {
        setText(text);
    }
    
}
