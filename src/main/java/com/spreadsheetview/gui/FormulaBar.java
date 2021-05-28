package com.spreadsheetview.gui;

import com.spreadsheetmodel.Spreadsheet;
import com.spreadsheetmodel.SpreadsheetEvent;
import com.spreadsheetmodel.SpreadsheetListener;
import com.spreadsheetmodel.cell.Cell;

import com.spreadsheetview.tui.SpreadsheetTui;


import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JTextField;




/**
 * The main frame of the Function Plotter application.
 * The "GUI".
 * The "GUI" knows the "model", it depends on the "model",
 * and it cannot exist without the "model".
 * The "model" of a SheetPanelSheetPanelSheetPanelListner is a Plot.
 */

public final class FormulaBar extends JPanel {

    private Cell currentCell;
    private Spreadsheet model;
    private SpreadsheetTui tui;
    private JTextField cellName;
    private JTextField contentField;
    private final Dimension preferredDimension = new Dimension(500, 100);
    private final StringBuilder stringBuilder = new StringBuilder(50);
    private final ArrayList<FormulaBarListener> listeners;

    /**
     * Create a formulaBar.
     * @param model The model to show.
     */
    public FormulaBar(final Spreadsheet model) {
        super();
        currentCell = model.getCurrentCell();
        this.model = model;
        tui = new SpreadsheetTui(model);
        listeners = new ArrayList<>();

        FlowLayout layout = new FlowLayout();
        layout.setAlignment(FlowLayout.LEFT);
        setLayout(layout);



        cellName = new JTextField(currentCell.getLocation().toString());

        // TODO add action listener to button
        cellName.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(final KeyEvent e) {
                super.keyPressed(e);
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    final String s = cellName.getText();
                    selectCell(s);
                }
            }
        });
        //cellName.setPreferredSize(new Dimension(500, 100));
        contentField = new JTextField(currentCell.getText());

        //adding listeners
        contentField.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                updateCurrentContent(contentField.getText());
            }
        });


        contentField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(final KeyEvent e) {
                super.keyPressed(e);
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    final String s = contentField.getText();
                    updateCurrentContent(s);
                }
            }
        });

        model.addListener(new SpreadsheetListener() {
            @Override
            public void spreadsheetChanged(Spreadsheet s, SpreadsheetEvent se) {
                updateCurrentCell(s.getCurrentCell());
            }
        });

        updateCurrentCell(model.getCurrentCell());
        add(cellName);
        add(contentField);


        this.setSize(new Dimension(SpreadsheetGui.DEFAULT_X, 30));
        updateSize();
        repaint();

    }

    private void selectCell(final String location) {
        model.selectCell(location);
        fireFormulaBarChanged();
    }

    private void updateCurrentCell(final Cell newCell) {
        currentCell = newCell;
        updateFormulaBar();
        stringBuilder.setLength(0);
        fireFormulaBarChanged();
    }

    private void updateCurrentContent(final String text) {
        model.updateCurrentCell(text);
    }

    private void updateFormulaBar() {
        final String formula = model.getFormula(currentCell);
        final String content = formula != null
                    ? formula
                    : currentCell.getText();
        cellName.setText(currentCell.getLocation().toString());
        contentField.setText(content);
    }

    // adding listeners
    /**
     * Adds a SheetViewListener to the list of listener objects.
     * @param li a SheetViewListener that will be added.
     */
    public void addListener(final FormulaBarListener li) {
        listeners.add(li);
    }

    /**
     * Removes a listener from the list of listeners.
     * @param li a Spreadsheet listener that will be removed.
     */
    public void removeListener(final FormulaBarListener li) {
        listeners.remove(li);
    }

    private void fireFormulaBarChanged() {
        for (final FormulaBarListener li : listeners) {
            li.formulaBarChanged(model);
        }
        updateSize();
    }

    /**
     * Updates the size.
     */
    public void updateSize() {
        if (getParent() != null) {
            this.setSize(new Dimension(getParent().getWidth(), 30));
        }


        cellName.setPreferredSize(new Dimension(40, 20));
        contentField.setPreferredSize(new Dimension(getWidth() - 50, 20));
        repaint();
    }


}