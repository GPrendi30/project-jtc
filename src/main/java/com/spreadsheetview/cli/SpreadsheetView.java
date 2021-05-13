package com.spreadsheetview.cli;

import com.spreadsheet.Spreadsheet;

public class SpreadsheetView {

    /**
     * Prints spreadsheet.
     * @param s the spreadsheet.
     */
    public void printSpreadSheet(final Spreadsheet s) {
        s.printCurrentSheet();
    }

}
