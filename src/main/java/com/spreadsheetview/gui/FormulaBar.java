package com.spreadsheetview.gui;

import com.spreadsheetmodel.Spreadsheet;
import com.spreadsheetmodel.SpreadsheetListener;
import com.spreadsheetmodel.cell.Cell;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import static java.lang.Thread.sleep;

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
    private JButton cellName;
    private JTextField contentField;
    private final static Dimension preferredDimension = new Dimension(500, 100);
    private final static StringBuilder STRING_BUILDER = new StringBuilder();
    private final ArrayList<FormulaBarListener> listeners;

    /**
     * Create a formulaBar.
     * @param model The model to show.
     */
    public FormulaBar(final Spreadsheet model) {
        super();
        currentCell = model.getCurrentCell();
        this.model = model;
        setLayout(new FlowLayout());
        listeners = new ArrayList<>();

        cellName = new JButton(currentCell.getLocation().toString());

        // TODO add action listener to button

        contentField = new JTextField(currentCell.getText());

        //adding listeners
        contentField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateCurrentContent(contentField.getText());
            }
        });

        contentField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    STRING_BUILDER.append(contentField.getText());
                    updateCurrentContent(STRING_BUILDER.toString());
                }
            }
        });

        model.addListener(new SpreadsheetListener() {
            @Override
            public void spreadsheetChanged(Spreadsheet s) {
                updateCurrentCell(s.getCurrentCell());
            }
        });

        updateCurrentCell(model.getCurrentCell());
        add(cellName);
        add(contentField);
        setPreferredSize(preferredDimension);
        //setMinimumSize(new Dimension(300,300));
        contentField.setPreferredSize(new Dimension(500, 50));
    }

    private void updateCurrentCell(Cell newCell) {
        currentCell = newCell;
        updateFormulaBar();
        STRING_BUILDER.setLength(0);
        fireFormulaBarChanged();
    }

    private void updateCurrentContent(String text) {
        model.updateCurrentCell(text);
    }

    private void updateFormulaBar() {
        cellName.setText(currentCell.getLocation().toString());
        contentField.setText(currentCell.getText());
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
    }


}