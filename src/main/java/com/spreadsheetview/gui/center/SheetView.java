package com.spreadsheetview.gui.center;

import com.spreadsheetmodel.Spreadsheet;
import com.spreadsheetmodel.SpreadsheetEvent;
import com.spreadsheetmodel.SpreadsheetEventType;
import com.spreadsheetmodel.SpreadsheetException;
import com.spreadsheetmodel.SpreadsheetListener;

import com.spreadsheetmodel.commands.CopyCommand;
import com.spreadsheetmodel.commands.CopyPasteStack;
import com.spreadsheetmodel.commands.CutCommand;
import com.spreadsheetmodel.commands.GrowSheetCommand;
import com.spreadsheetmodel.commands.PasteCommand;
import com.spreadsheetmodel.commands.SelectCellCommand;
import com.spreadsheetmodel.commands.SortColumnCommand;
import com.spreadsheetview.gui.GuiHandlerUtil;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.util.ArrayList;
import java.util.HashMap;


import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;


public class SheetView extends JScrollPane {

    private CustomTable mainGrid;
    private Spreadsheet model;
    private final ArrayList<SheetViewListener> listeners;
    private final HashMap<String, TableModel> tableModels;
    private final TableModelListener tableListener = new TableModelListener() {

        @Override
        public void tableChanged(final TableModelEvent e) {
            final int row = mainGrid.getSelectedRow() + 1;
            final int column = mainGrid.getSelectedColumn();

            try {
                model.getCurrentSheet().update(
                        row,
                        column,
                        (String) mainGrid.getValueAt(row - 1, column));
            } catch (SpreadsheetException exception) {
                exception.printStackTrace();
            }
            redraw();
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

        GuiHandlerUtil.handleCommand(new GrowSheetCommand("Horizontally", 20));
        GuiHandlerUtil.handleCommand(new GrowSheetCommand("Vertically", 100));

        addTableModel();
        selectSheetModel();

        addMouseAdapter();
        addKeyListener();
        addListenerToModel();

        mainGrid.getColumnModel().getColumn(0).setPreferredWidth(35);
        mainGrid.setAutoCreateRowSorter(false);
        mainGrid.setAutoResizeMode(CustomTable.AUTO_RESIZE_OFF);
        add(mainGrid);
        setViewportView(mainGrid);
    }

    private void addMouseAdapter() {
        mainGrid.addMouseListener(new MouseAdapter() {

            public void mouseClicked(final MouseEvent e) {
                super.mouseClicked(e);

                final int row = mainGrid.getSelectedRow();
                final int column = mainGrid.getSelectedColumn();
                if (row >= 0 && column > 0) {
                    GuiHandlerUtil.handleCommand(new SelectCellCommand(row + 1, column));
                }

                final javax.swing.PopupFactory p = new javax.swing.PopupFactory();

                final java.awt.Point pointer = java.awt.MouseInfo.getPointerInfo().getLocation();
                final javax.swing.Popup pop = p.getPopup(
                        null,
                        new MouseRightClickPanel(),
                        pointer.x,
                        pointer.y);

                if (e.getButton() == MouseEvent.BUTTON3) {
                    final java.util.Timer t = new java.util.Timer();
                    t.schedule(new java.util.TimerTask() {
                        @Override
                        public void run() {
                            pop.hide();
                        }
                    }, 500, 3500);
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
                if (row > 0 && column > 1
                    && row < mainGrid.getRowCount() - 1 && column < mainGrid.getColumnCount() - 1) {
                    switch (keyEvent.getKeyCode()) {
                        case KeyEvent.VK_UP:
                            row = row - 1;
                            break;
                        case KeyEvent.VK_DOWN:
                            row = row + 1;
                            break;
                        case KeyEvent.VK_LEFT:
                            column = column - 1;
                            break;
                        case KeyEvent.VK_RIGHT:
                            column = column + 1;
                            break;
                        default:
                            break;
                    }
                    GuiHandlerUtil.handleCommand(new SelectCellCommand(row + 1, column));
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
                    fireSheetViewChanged();
                }
            }
        });
    }


    private void addTableModel() {
        if (tableModels.containsKey(model.getCurrentSheetName())) {
            return;
        }

        redraw();
    }

    private void redraw() {
        Object[][] tableData = null;
        try {
            tableData = model.getCurrentSheet().createDataTable();
        } catch (SpreadsheetException exception) {
            exception.printStackTrace();
        }
        String[] columns = model.getCurrentSheet().getColumns();
        columns[0] = "";

        final DefaultTableModel tableModel = new DefaultTableModel(tableData, columns);

        tableModels.put(model.getCurrentSheetName(), tableModel);
        mainGrid.setModel(tableModel);
        mainGrid.getColumnModel().getColumn(0).setPreferredWidth(35);
        tableModel.addTableModelListener(tableListener);
        repaint();
        fireSheetViewChanged();
    }

    private void selectSheetModel() {
        mainGrid.setModel(tableModels.get(model.getCurrentSheetName()));
        mainGrid.getColumnModel().getColumn(0).setPreferredWidth(35);
        repaint(0, 0, mainGrid.getWidth(), mainGrid.getHeight());
        fireSheetViewChanged();
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

    private class MouseRightClickPanel extends JPanel {

        public MouseRightClickPanel() {
            super();
            setLayout(new java.awt.GridLayout(4,1));
            final JButton copy = new JButton("copy");
            copy.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(final ActionEvent actionEvent) {
                    GuiHandlerUtil.handleCommand(new CopyCommand(CopyPasteStack.getInstance()));
                }
            });

            final JButton paste = new JButton("paste");
            paste.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(final ActionEvent actionEvent) {
                    GuiHandlerUtil.handleCommand(new PasteCommand(CopyPasteStack.getInstance()));
                }
            });

            final JButton cut = new JButton("cut");
            cut.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(final ActionEvent actionEvent) {
                    GuiHandlerUtil.handleCommand(new CutCommand(CopyPasteStack.getInstance()));
                }
            });

            final JButton sort = new JButton("sort");
            sort.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(final ActionEvent actionEvent) {
                    GuiHandlerUtil.handleCommand(
                            new SortColumnCommand(mainGrid.getSelectedColumn()));
                }
            });

            setSize(new Dimension(100,50));
            add(copy);
            add(paste);
            add(cut);
            add(sort);

        }
    }

    private void fireSheetViewChanged() {
        for (final SheetViewListener li : listeners) {
            li.sheetViewChanged(this);

            repaint();
        }
    }

}


