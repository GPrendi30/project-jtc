package com.spreadsheetmodel;

import java.io.Serializable;

/**
 * Events of a Spreadsheet.
 *
 * @author Di Pietro Enrico, Prendi Gerald.
 *
 */
public class SpreadsheetEvent implements Serializable {

    private String message;
    private SpreadsheetEventType eventType;

    /**
     * Creates a spreadsheet event.
     * @param message a String.
     * @param eventType the type of the event.
     */
    public SpreadsheetEvent(final String message, final SpreadsheetEventType eventType) {
        this.message = message;
        this.eventType = eventType;

    }

    /**
     * Gets the event type.
     * @return SpreadsheetEventType the event.
     */
    public SpreadsheetEventType getId() {
        return eventType;
    }

    /**
     * Gets the event message.
     * @return String the spreadsheet message.
     */
    public String getMessage() {
        return message;
    }


}
