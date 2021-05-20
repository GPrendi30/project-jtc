package com.spreadsheetview.gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.*;

import com.spreadsheetmodel.*;
import com.spreadsheetmodel.cell.*;

import static java.lang.Thread.sleep;

/**
 * The main frame of the Function Plotter application.
 * The "GUI".
 * The "GUI" knows the "model", it depends on the "model",
 * and it cannot exist without the "model".
 * The "model" of a SpreadsheetFrame is a Plot.
 */

public final class FormulaBar extends JPanel {

    private Cell currentCell;
    private Spreadsheet model;
    private JButton cellName;
    private JTextField contentField;
    private final static Dimension preferredDimension = new Dimension(500, 100);
    private final static StringBuilder STRING_BUILDER = new StringBuilder();
    /**
     * Create a new SpreadsheetFrame for the given Plot.
     * @param model The model to show.
     */
    public FormulaBar(final Spreadsheet model) {
        super();
        currentCell = model.getCurrentCell();
        this.model = model;
        setLayout(new FlowLayout());

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
                STRING_BUILDER.append(contentField.getText());
                updateCurrentContent(STRING_BUILDER.toString());
            }
        });

        model.addListener(new SpreadsheetListener() {
            @Override
            public void spreasheetChanged(Spreadsheet s) {
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
    }

    private void updateCurrentContent(String text) {
        model.updateCurrentCell(text);
    }

    private void updateFormulaBar() {
        cellName.setText(currentCell.getLocation().toString());
        contentField.setText(currentCell.getText());
    }

    public static void main(String[] args) throws InterruptedException {
        Spreadsheet s = new Spreadsheet();
        s.selectCell(3,3);
        s.updateCurrentCell("g");
        FormulaBar f = new FormulaBar(s);
        JFrame frame = new JFrame();
        frame.setLayout(new BorderLayout());
        frame.add(f, BorderLayout.NORTH);
        frame.setSize(new Dimension(500, 500));
        s.addListener(new SpreadsheetListener() {
            @Override
            public void spreasheetChanged(Spreadsheet s) {
                System.out.println(s.getCurrentCell().getText());
            }
        });
        frame.pack();
        frame.setVisible(true);
        sleep(5);
        s.getCurrentSheet().update(1,4,"t");
        s.selectCell(1,4);
        frame.pack();
    }

}