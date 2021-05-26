package com.spreadsheetview.gui;

import com.spreadsheetmodel.Spreadsheet;
import com.spreadsheetmodel.SpreadsheetListener;
import com.spreadsheetmodel.cell.Cell;
import com.spreadsheetmodel.sheet.Sheet;

import org.w3c.dom.events.MouseEvent;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;



public class SheetView extends JScrollPane {

    private JTable mainGrid;
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

        model.grow("Horizontally", 20);
        model.grow("Vertically", 20);

        Object[][] tableData = current.createDataTable();
        String[] columns = model.getCurrentSheet().getColumns();

        columns[0] = "";

        mainGrid = new JTable(tableData, columns);
        mainGrid.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        
        // select the cells on keys
        mainGrid.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(final KeyEvent e) {
                super.keyReleased(e);

                final int row = mainGrid.getSelectedRow();
                final int column = mainGrid.getSelectedColumn();

                model.selectCell(row, column);

                // when you press enter, the selected cell is evaluated also in the view
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    mainGrid.setValueAt(current.get(row - 1, column).getText(), row - 1, column);
                }

            }
        });

        // selects the cell on mouse click
        mainGrid.addMouseListener(new MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) {
                super.mouseClicked(e);

                final int row = mainGrid.getSelectedRow();
                final int column = mainGrid.getSelectedColumn();

                model.selectCell(row, column);

            }
        });

        // updates the sheet's cells when one is modified
        mainGrid.getModel().addTableModelListener(new TableModelListener() {
            public void tableChanged(TableModelEvent e) {

                final int row = mainGrid.getSelectedRow();
                final int column = mainGrid.getSelectedColumn();

                String content = (String)mainGrid.getValueAt(row, column);
                
                current.update(row, column, content);

                // select the cell 
                model.selectCell(row, column);

                mainGrid.repaint();
            }
        });
        
        add(mainGrid);

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

    /*
    private void drawSheet(final Sheet current) {
        mainGrid.setLayout(new GridLayout(current.sizeX(), current.sizeY()));
        for (int x = 0; x < current.sizeX(); x++) {
            for (int y = 0; y < current.sizeY(); y++) {
                final Cell c = current.get(x,y);
                final CellView cv = new CellView(c);
                mainGrid.add(cv);
            }
        }
    } */

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

