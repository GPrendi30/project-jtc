package com.spreadsheetview;

import com.spreadsheetmodel.Spreadsheet;

import java.io.Serializable;

public interface SpreadsheetView extends Serializable {

    /**
     * Starts the view.
     */
    public void init();

    /**
     * Prints the view.
     */
    public void updateView();

    /**
     * Update the model of the view.
     * @param newModel the new model.
     */
    public void updateModel(final Spreadsheet newModel);

}
