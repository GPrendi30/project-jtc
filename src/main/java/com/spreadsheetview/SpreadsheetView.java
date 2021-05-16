package com.spreadsheetview;

import com.spreadsheet.Spreadsheet;

public interface SpreadsheetView {

    /**
     * Starts the view.
     */
    public void init();

    /**
     * Prints the view.
     * @param model a spreadsheet.
     */
    public void printSpreadsheet(Spreadsheet model);

}
