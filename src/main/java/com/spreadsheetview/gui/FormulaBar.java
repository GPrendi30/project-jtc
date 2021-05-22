package com.spreadsheetview.gui;

import com.spreadsheetmodel.Spreadsheet;
import com.spreadsheetmodel.SpreadsheetListener;
import com.spreadsheetmodel.cell.Cell;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.JButton;
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
    private JButton cellName;
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
        setLayout(new FlowLayout());
        listeners = new ArrayList<>();

        cellName = new JButton(currentCell.getLocation().toString());

        // TODO add action listener to button

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
                    stringBuilder.append(contentField.getText());
                    updateCurrentContent(stringBuilder.toString());
                }
            }
        });

        model.addListener(new SpreadsheetListener() {
            @Override
            public void spreadsheetChanged(final Spreadsheet s) {
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