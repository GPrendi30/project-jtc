package com.spreadsheetview;

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

}
