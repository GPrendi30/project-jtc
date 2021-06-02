package com.spreadsheetview.gui.center;

import com.spreadsheetmodel.Spreadsheet;

import com.spreadsheetmodel.SpreadsheetEvent;
import com.spreadsheetmodel.SpreadsheetEventType;
import com.spreadsheetmodel.SpreadsheetException;
import com.spreadsheetmodel.SpreadsheetListener;


import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.util.ArrayList;
import java.util.HashMap;

import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.Popup;
import javax.swing.PopupFactory;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;


public class SheetView extends JScrollPane {

    private JTable mainGrid;
    private Spreadsheet model;
    private final ArrayList<SheetViewListener> listeners;
    private final HashMap<String, TableModel> tableModels;
    private final TableModelListener tableListener = new TableModelListener() {

        @Override
        public void tableChanged(final TableModelEvent e) {
            final int row = mainGrid.getSelectedRow() + 1;
            final int column = mainGrid.getSelectedColumn();

                model.getCurrentSheet().update(
                        row,
                        column,
                        (String) mainGrid.getValueAt(row - 1, column));
        }

    };

    /**
     * Create a sheetView.
     * @param model a Spreadsheet.
     */
    public SheetView(final Spreadsheet model) {
        super();
        this.model = model;
        listeners = new ArrayList<>();
        tableModels = new HashMap<>();

        mainGrid = new CustomTable();

        try {
            model.grow("Horizontally", 20);
            model.grow("Vertically", 100);
        } catch (SpreadsheetException exception) {
            JOptionPane.showMessageDialog(null, exception.getMessage());
        }

        addTableModel();
        selectSheetModel();

        addMouseAdapter();
        addKeyListener();
        addListenerToModel();

        mainGrid.getColumnModel().getColumn(0).setPreferredWidth(35);
        mainGrid.setAutoCreateRowSorter(false);
        mainGrid.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        add(mainGrid);
        setViewportView(mainGrid);
    }

    private void addMouseAdapter() {
        mainGrid.addMouseListener(new MouseAdapter() {

            public void mouseClicked(final MouseEvent e) {
                super.mouseClicked(e);

                final int row = mainGrid.getSelectedRow();
                final int column = mainGrid.getSelectedColumn();
                if (row >= 0 && column >= 0) {
                    model.selectCell(row + 1, column);
                }

                final PopupFactory p = new PopupFactory();
                final JPanel jp = new JPanel();
                jp.setSize(new Dimension(100, 100));
                jp.add(new Button("hello"));

                final Point pointer = MouseInfo.getPointerInfo().getLocation();
                final Popup pop = p.getPopup(null, jp, pointer.x, pointer.y);
                if (e.getButton() == MouseEvent.BUTTON3) {
                    final Timer t = new Timer();
                    t.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            pop.hide();
                        }
                    }, 500, 600);
                    pop.show();
                }

            }
        });
    }

    private void addKeyListener() {
        mainGrid.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(final KeyEvent keyEvent) {
                // not needed.
            }

            @Override
            public void keyPressed(final KeyEvent keyEvent) {
                int row = mainGrid.getSelectedRow();
                int column = mainGrid.getSelectedColumn();
                switch (keyEvent.getKeyCode()) {
                    case KeyEvent.VK_UP:
                        row = row - 1;
                        model.selectCell(row + 1, column);
                        break;
                    case KeyEvent.VK_DOWN:
                        row = row + 1;
                        model.selectCell(row + 1, column);
                        break;
                    case KeyEvent.VK_LEFT:
                        column = column - 1;
                        model.selectCell(row + 1, column);
                        break;
                    case KeyEvent.VK_RIGHT:
                        column = column + 1;
                        model.selectCell(row + 1, column);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void keyReleased(final KeyEvent keyEvent) {
                // not needed.
            }
        }
        );
    }

    private void addListenerToModel() {
        model.addListener(new SpreadsheetListener() {
            @Override
            public void spreadsheetChanged(final Spreadsheet s,final SpreadsheetEvent se) {

                if (se.getId() == SpreadsheetEventType.SHEET_SELECTED) {
                    mainGrid.changeSelection(1,1, true,true);
                    addTableModel();
                    selectSheetModel();
                    mainGrid.clearSelection();
                }

                if (se.getId() == SpreadsheetEventType.SHEET_CHANGED) {
                    redraw();
                }

                if (se.getId() == SpreadsheetEventType.CELL_SELECTED) {
                    final int col = model.getCurrentCell().getLocation().getIntColumn();
                    final DefaultTableCellRenderer MyHeaderRender = new DefaultTableCellRenderer();
                    MyHeaderRender.setBackground(new Color(60, 179, 113));

                    for (int i = 0; i < mainGrid.getColumnCount(); i++) {
                        if (i == col) {
                            mainGrid.getColumnModel().getColumn(i)
                                    .setHeaderRenderer(MyHeaderRender);
                            continue;
                        }
                        mainGrid.getColumnModel().getColumn(i).setHeaderRenderer(
                                mainGrid.getTableHeader().getDefaultRenderer());
                    }

                }
                if (se.getId() == SpreadsheetEventType.CELL_CHANGED) {
                    final int row = model.getCurrentCell().getLocation().getRow();
                    final int col = model.getCurrentCell().getLocation().getIntColumn();
                    mainGrid.setValueAt(model.getCurrentCell().getText(), row - 1, col);
                }
            }
        });
    }


    private void addTableModel() {
        if (tableModels.containsKey(model.getCurrentSheetName())) {
            return;
        }

        final Object[][] tableData = model.getCurrentSheet().createDataTable();
        String[] columns = model.getCurrentSheet().getColumns();
        columns[0] = "";

        final DefaultTableModel tableModel = new DefaultTableModel(tableData, columns);

        tableModels.put(model.getCurrentSheetName(), tableModel);
        mainGrid.setModel(tableModel);

        mainGrid.getTableHeader().setReorderingAllowed(false);
        tableModel.addTableModelListener(tableListener);
    }

    private void redraw() {
        final Object[][] tableData = model.getCurrentSheet().createDataTable();
        String[] columns = model.getCurrentSheet().getColumns();
        columns[0] = "";

        final DefaultTableModel tableModel = new DefaultTableModel(tableData, columns);
        tableModel.addTableModelListener(tableListener);
        tableModels.put(model.getCurrentSheetName(), tableModel);
        mainGrid.setModel(tableModel);
        mainGrid.getColumnModel().getColumn(0).setPreferredWidth(35);
        repaint();
    }

    private void selectSheetModel() {
        mainGrid.setModel(tableModels.get(model.getCurrentSheetName()));
        mainGrid.getColumnModel().getColumn(0).setPreferredWidth(35);
        repaint(0, 0, mainGrid.getWidth(), mainGrid.getHeight());
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

}


