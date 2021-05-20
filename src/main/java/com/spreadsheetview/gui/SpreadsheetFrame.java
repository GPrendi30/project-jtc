package com.spreadsheetview.gui;

import com.spreadsheetmodel.Spreadsheet;

import javax.swing.*;
import java.awt.*;


public class SpreadsheetFrame extends JPanel {

    private Spreadsheet model;
    private GridLayout grid;
    private FormulaBar formulaBar;
    private TabsView tabsBar;
    
    public SpreadsheetFrame(Spreadsheet model) {
        super();
        this.model = model;

        setLayout(new BorderLayout());
        SheetView sv = new SheetView(model);
        add(sv, BorderLayout.CENTER);

        formulaBar = new FormulaBar(model);
        // move it to end of constructor
        add(formulaBar, BorderLayout.NORTH);

        tabsBar = new TabsView(model);
        // move it to end of constructor
        add(tabsBar, BorderLayout.SOUTH);


    }

}
