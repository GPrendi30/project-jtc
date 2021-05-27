package com.spreadsheetview.gui;

import com.spreadsheetmodel.Spreadsheet;
import com.spreadsheetmodel.SpreadsheetEvent;
import com.spreadsheetmodel.SpreadsheetEventType;
import com.spreadsheetmodel.SpreadsheetListener;
import com.spreadsheetmodel.sheet.Sheet;


import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;


public class SheetView extends JScrollPane {

    private JTable mainGrid;
    private Spreadsheet model;
    private final ArrayList<SheetViewListener> listeners;
    //TODO

    /**
     * Create a sheetView.
     * @param model a Spreadsheet.
     */
    public SheetView(final Spreadsheet model) {
        super();
        this.model = model;
        final Sheet current = model.getCurrentSheet();
        listeners = new ArrayList<>();

        model.grow("Horizontally", 25);
        model.grow("Vertically", 20);

        //draw();

        final Object[][] tableData = model.getCurrentSheet().createDataTable();
        String[] columns = model.getCurrentSheet().getColumns();

        columns[0] = "";

        DefaultTableModel tableModel = new DefaultTableModel(tableData, columns);


        mainGrid = new JTable(tableModel) {

            public TableCellRenderer getCellRenderer(int row, int column) {

                if (isRowSelected(row) && column == 0){

                    return new ColumnSelector();
                }

                if(isRowSelected(row) && isColumnSelected(column)) {
                    return new ColorRenderer();
                }

                if (column == 0) {
                    return mainGrid.getTableHeader().getDefaultRenderer();
                }
                // else...
                return super.getCellRenderer(row, column);
            }

            public boolean isCellEditable(int rowindex, int colindex)
            {
                if(colindex==0)
                {
                    return false; // Disallow Column 0
                }
                else
                {
                    return true;  // Allow the editing
                }
            }

        };
        mainGrid.getColumnModel().getColumn(0).setPreferredWidth(35);

        //TableCellRenderer colorRenderer = new ColorRenderer();
        //mainGrid.setDefaultRenderer(String.class, colorRenderer);

        //  Override default renderer for a specific column
        mainGrid.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        add(mainGrid);
        setViewportView(mainGrid);
    }
        /*
        mainGrid.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        
        // select the cells on keys
        mainGrid.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(final KeyEvent e) {



                // when you press enter, the selected cell is evaluated also in the view
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    final int row = mainGrid.getSelectedRow() + 1;
                    final int column = mainGrid.getSelectedColumn();

                    //model.selectCell(row, column);
                    //fireGridUpdate();
                    //System.out.println(row + " " + column + " " + mainGrid.getValueAt(row, column).toString());
                    //model.updateCurrentCell(mainGrid.getValueAt(row, column).toString());
                    //fireCellUpdate(row, column);

                }

            }
        });

        // selects the cell on mouse click
        mainGrid.addMouseListener(new MouseAdapter() {

            public void mouseClicked(final MouseEvent e) {
                super.mouseClicked(e);

                final int row = mainGrid.getSelectedRow();
                final int column = mainGrid.getSelectedColumn();
                System.out.println(row + " " + column);
                model.selectCell(row+1, column);

            }
        });

        // updates the sheet's cells when one is modified



        mainGrid.getModel().addTableModelListener(new TableModelListener() {

            public void tableChanged(final TableModelEvent e) {
                final int row = mainGrid.getSelectedRow();
                final int column = mainGrid.getSelectedColumn();
            }
        });
        
        add(mainGrid);

        model.addListener(new SpreadsheetListener() {
            @Override
            public void spreadsheetChanged(Spreadsheet s, SpreadsheetEvent se) {
                //fireSheetViewChanged();
                //TODO add a type to the Spreadsheetchanged() method.
                //fireGridUpdate();
                //mainGrid.clearSelection();
                final int row = model.getCurrentCell().getLocation().getRow()-1;
                final int column = model.getCurrentCell().getLocation().getIntColumn();
                mainGrid.setValueAt(model.getCurrentCell().getText(), row, column);
                mainGrid.changeSelection(row, column, true, false);
                //drawSheet(model.getCurrentSheet());
                //redraw();
                switch (se.getID()) {
                    case SHEET_ADDED:
                    case SHEET_CHANGED:
                    case SHEET_SELECTED:
                    default:
                        System.out.println("draw");
                        draw();
                        repaint();
                }
            }


        });
        setViewportView(mainGrid);
         */

    // adding listeners

    private void fireSheetViewChanged() {
        for (final SheetViewListener li : listeners) {
            li.sheetViewChanged(model);
            repaint();
        }
    }

    private void fireCellUpdate(final int row, final int col) {

        AbstractTableModel tableModel = (AbstractTableModel) mainGrid.getModel();
        tableModel.fireTableCellUpdated(row, col);
        //System.out.println(tableModel.getValueAt(row, col).toString());
        //model.updateCurrentCell(mainGrid.getValueAt(row, col).toString());
    }

    public void draw() {
        //remove(mainGrid);
        System.out.println("redraw");


        final Object[][] tableData = model.getCurrentSheet().createDataTable();
        String[] columns = model.getCurrentSheet().getColumns();


        columns[0] = "";

        mainGrid = new JTable(tableData, columns)
        {
            public boolean isCellEditable(int rowindex, int colindex)
            {
                if(colindex==0)
                {
                    return false; // Disallow Column 0
                }
                else
                {
                    return true;  // Allow the editing
                }
            }
        };
    }

    private void fireGridUpdate() {

        AbstractTableModel tableModel = (AbstractTableModel) mainGrid.getModel();
        tableModel.fireTableDataChanged();
    }


    /**
     * Removes a listener from the list of listeners.
     * @param li a Spreadsheet listener that will be removed.
     */
    public void removeListener(final SheetViewListener li) {
        listeners.remove(li);
    }

    /**
     * Adds a SheetViewListener to the list of listener objects.
     * @param li a SheetViewListener that will be added.
     */
    public void addListener(final SheetViewListener li) {
        listeners.add(li);
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

    private class ColorRenderer extends DefaultTableCellRenderer {

        private static final long serialVersionUID = 1L;

        @Override
        public Component getTableCellRendererComponent(
                JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            if (isSelected)
                setBackground(new Color(172,225,175));


            else
                setBackground(table.getBackground());
            return this;
        }
    }

    private class ColumnSelector extends DefaultTableCellRenderer {

        private static final long serialVersionUID = 1L;

        @Override
        public Component getTableCellRendererComponent(
                JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                if (isSelected)
                    setBackground(new Color(60,179,113));


            return this;
        }
    }
}

