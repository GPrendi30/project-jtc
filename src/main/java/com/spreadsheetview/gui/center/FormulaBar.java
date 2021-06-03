package com.spreadsheetview.gui.center;

import com.spreadsheetmodel.Spreadsheet;
import com.spreadsheetmodel.SpreadsheetEvent;
import com.spreadsheetmodel.SpreadsheetListener;
import com.spreadsheetmodel.cell.Cell;

import com.spreadsheetmodel.commands.SelectCellCommand;
import com.spreadsheetmodel.commands.UpdateCellCommand;
import com.spreadsheetview.gui.GuiHandler;

import java.awt.BorderLayout;
import java.awt.Dimension;
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
    private JTextField cellName;
    private JTextField contentField;
    private final ArrayList<FormulaBarListener> listeners;

    /**
     * Create a formulaBar.
     * @param model The model to show.
     */
    public FormulaBar(final Spreadsheet model) {
        super();
        currentCell = model.getCurrentCell();
        this.model = model;
        listeners = new ArrayList<>();

        final BorderLayout layout = new BorderLayout();
        setLayout(layout);
        layout.setHgap(5);
        layout.setVgap(10);

        cellName = new JTextField(currentCell.getLocation().toString());


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
            public void spreadsheetChanged(final Spreadsheet s, final SpreadsheetEvent se) {
                updateCurrentCell(s.getCurrentCell());
            }
        });
        cellName.setPreferredSize(new Dimension(40, this.getHeight()));

        updateCurrentCell(model.getCurrentCell());
        add(cellName, BorderLayout.WEST);
        add(contentField, BorderLayout.CENTER);


        //this.setSize(new Dimension(SpreadsheetGui.DEFAULT_X, 30));
        updateSize();
        repaint();
    }

    private void selectCell(final String location) {
        GuiHandler.handleCommand(new SelectCellCommand(location));
        fireFormulaBarChanged();
    }

    private void updateCurrentCell(final Cell newCell) {
        currentCell = newCell;
        updateFormulaBar();
        fireFormulaBarChanged();
    }

    private void updateCurrentContent(final String text) {
        GuiHandler.handleCommand(new UpdateCellCommand(model.getCurrentCell(), text));
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
    }


}