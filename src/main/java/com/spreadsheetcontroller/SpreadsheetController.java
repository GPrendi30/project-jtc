package com.spreadsheetcontroller;


import com.spreadsheet.Spreadsheet;
import com.spreadsheetview.SpreadsheetView;
import com.spreadsheetview.tui.SpreadsheetTUI;


public class SpreadsheetController {

    private final Spreadsheet model;
    private final SpreadsheetView view;

    /**
     * Creates a new SpreadsheetController.
     * @param s a SpreadSheet.
     * @param sv a SpreadSheetView.
     */
    public SpreadsheetController(final Spreadsheet s, final SpreadsheetView sv) {
        model = s;
        view = sv;
    }

    /**
     * Main function.
     * @param args any args that you want.
     */
    public static void main(final String[] args) {

        final Spreadsheet s = new Spreadsheet();
        final SpreadsheetTUI tui = new SpreadsheetTUI(s);
        final SpreadsheetController controller = new SpreadsheetController(s, tui);
        controller.start();
    }

    private void start() {
        view.init();
    }

    /**
     * Updates the view.
     */
    public void updateView() {
        view.printSpreadsheet(model);
    }

}
