package com.spreadsheetview.tui;

import com.spreadsheetmodel.Spreadsheet;
import com.spreadsheetmodel.cell.CellLocation;
import com.spreadsheetmodel.sheet.Sheet;
import com.spreadsheetview.SpreadsheetView;

import java.util.HashMap;
import java.util.Iterator;

public class SpreadsheetTui implements SpreadsheetView {

    private final Spreadsheet model;

    /**
     * Creates a new TUI.
     * @param s a Spreadsheet.
     */
    public SpreadsheetTui(final Spreadsheet s) {
        model = s;
    }

    @Override
    public void init() {
        //do nothing.
    }

    /**
     * Updates the view.
     */
    public void updateView() {
        final Sheet currentSheet = model.getCurrentSheet();
        printSheet(currentSheet);
        printSheetNames(model.getSheets());
        printFormulas(currentSheet);

    }

    private void printSheet(final Sheet s) {
        System.out.println(" ###########  "  + s.getTableName() + "  ############# ");
        for (int x = 0; x <= s.sizeX(); x++) {
            if (x == 0) {
                /* table upper border */
                System.out.println("_______________________________________"
                        +
                        "__________________________________________");
            }
            for (int y = 0; y <= s.sizeY(); y++) {
                // for y in col 0 and 1
                if (y == 0 || y == 1) {
                    System.out.print("|");
                }
                // printing the cell
                final String g = s.get(x, y).getText();
                System.out.printf(" %10s |", g);
            }
            System.out.println();

            // table down border
            if (x == s.sizeX()) {
                System.out.println("__________________________________________"
                        +
                        "_______________________________________");
            }

        }
    }

    private void printSheetNames(final Sheet[] sheets) {
        final Sheet currentSheet = model.getCurrentSheet();
        for (final Sheet t : sheets) {
            String tName = t.getTableName();
            if (tName.equals(currentSheet.getTableName())) {
                tName += "*";
            }
            System.out.print("          \\ " + tName + " / ");
        }
        System.out.println();
    }

    /**
     * Prints the formulas' Table.
     */
    private void printFormulas(final Sheet s) {
        final HashMap<CellLocation, String> formulas = s.getFormulaTable();
        final Iterator<CellLocation> cellLocationIterator = formulas.keySet().iterator();
        System.out.println(" \n\n|     FORMULAE TABLE      |");
        System.out.println("---------------------------");
        for (final String c : formulas.values())
        {
            System.out.println("----------------------------");
            System.out.println(" | " + cellLocationIterator.next() + " : " + c + " | ");

        }
    }

}
