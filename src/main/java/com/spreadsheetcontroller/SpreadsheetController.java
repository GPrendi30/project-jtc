package com.spreadsheetcontroller;

import com.spreadsheetmodel.Spreadsheet;
import com.spreadsheetmodel.SpreadsheetException;
import com.spreadsheetmodel.commands.Invoker;
import com.spreadsheetview.SpreadsheetView;
import com.spreadsheetview.gui.SpreadsheetGui;
import com.spreadsheetview.tui.SpreadsheetTui;

/**
 * The controller of the Spreadsheet.
 *
 * @author Di Pietro Enrico, Prendi Gerald.
 *
 */
public class SpreadsheetController {


    private final transient SpreadsheetView view;

    /**
     * Creates a new SpreadsheetController.
     * @param sv a SpreadSheetView.
     */
    public SpreadsheetController(final SpreadsheetView sv) {
        view = sv;
    }


    /**
     * Main function.
     * @param args any args that you want.
     * @throws SpreadsheetException throws
     */
    public static void main(final String[] args) throws SpreadsheetException {
        boolean guiBool = true;

        if (args.length != 0) {
            guiBool = hasGui(args[0]);
        }

        final Spreadsheet s = new Spreadsheet(5,5);

        Invoker.setReceiver(s);
        final SpreadsheetView view = guiBool
                        ?   new SpreadsheetGui(s)
                        :   new SpreadsheetTui(s);

        final SpreadsheetController controller = new SpreadsheetController(view);
        controller.start();
    }

    private static boolean hasGui(final String arg) {
        return "gui".equals(arg);
    }

    private void start() {
        view.init();
    }


}
