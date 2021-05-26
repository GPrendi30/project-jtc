package com.spreadsheetview.tui;

import com.spreadsheetmodel.Spreadsheet;
import com.spreadsheetmodel.SpreadsheetListener;
import com.spreadsheetmodel.cell.Cell;
import com.spreadsheetmodel.cell.CellLocation;
import com.spreadsheetmodel.sheet.Sheet;
import com.spreadsheetview.SpreadsheetView;

import java.util.HashMap;
import java.util.Iterator;

public class SpreadsheetTui implements SpreadsheetView {

    private final Spreadsheet model;
    //private int selectedCellX;
    //private int selectedCellY;

    /**
     * Creates a new TUI.
     * @param s a Spreadsheet.
     */
    public SpreadsheetTui(final Spreadsheet s) {
        model = s;
        model.addListener(new SpreadsheetListener() {
            @Override
            public void spreadsheetChanged(final Spreadsheet s) {
                updateView();
            }
        });
    }

    @Override
    public void init() {
        //do nothing.
    }

    /**
     * Updates the view.
     */
    public void updateView() {
        resetTerminalView();
        final Sheet currentSheet = model.getCurrentSheet();
        printSheet(model);
        printSheetNames(model.getSheets());
        printFormulas(currentSheet);
    }

    private void resetTerminalView() {
        // this is the flush escape character for unix-like terminals.
        System.out.print("\033[H\033[2J");
        System.out.flush();
        // TODO test for windows machines.
    }

    /**
     * Prints the sheet.
     * @param spreadsheet a Spreadsheet.
     */
    public void printSheet(final Spreadsheet spreadsheet) {
        final Sheet s = spreadsheet.getCurrentSheet();
        System.out.println(" ###########  "  + s.getTableName() + "  ############# ");
        final String currentLocation = model.getCurrentCell().getLocation().toString();
        int space = 10;
        for (int x = 0; x <= s.sizeX(); x++) {
            if (x == 0) {
                /* table upper border */
                System.out.println("_______________________________________"
                        +
                        "__________________________________________");
            }
            for (int y = 0; y <= s.sizeY(); y++) {
                space = 10;
                // for y in col 0 and 1
                if (y == 0) {
                    System.out.print("|");
                    space = 3;
                }
                // printing the cell
                final Cell g = s.getCell(x, y);
                final String thisCellLocation = g.getLocation().toString();

                if (currentLocation.equals(thisCellLocation)) {
                    System.out.print("x");
                    space--;
                }

                System.out.printf(" %" + space + "s |", g.getText());
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

    // testing keyListener;
}
