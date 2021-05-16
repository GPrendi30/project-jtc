package com.spreadsheetview.cli;

import com.spreadsheet.Spreadsheet;
import com.spreadsheetview.SpreadsheetView;

public abstract class SpreadsheetCli implements SpreadsheetView {

    /**
     * Prints spreadsheet.
     * @param s the spreadsheet.
     */
    public void printSpreadsheet(final Spreadsheet s) {
        s.printCurrentSheet();
    }

}
