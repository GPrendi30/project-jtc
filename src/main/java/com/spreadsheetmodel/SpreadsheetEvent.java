package com.spreadsheetmodel;

public class SpreadsheetEvent {

    private String message;
    private SpreadsheetEventType eventType;

    public SpreadsheetEvent(final String message, final SpreadsheetEventType eventType) {

        this.message= message;
        this.eventType = eventType;

    }

    public SpreadsheetEventType getID() {
        return eventType;
    }

}
