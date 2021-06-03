package com.spreadsheetmodel;

/**
 * The exceptions for the class Spreadsheet.
 *
 * @author Di Pietro Enrico, Prendi Gerald.
 *
 */
public class SpreadsheetException extends Exception {

    /**
     * Creates an SpreadsheetException.
     */
    public SpreadsheetException() {
        super();
    }

    /**
     * Creates an SpreadsheetException with a message.
     *
     * @param message a String message associated with the Exception.
     */
    public SpreadsheetException(final String message) {
        super(message);
    }

    /**
     * Creates an SpreadsheetException with a message and a cause.
     *
     * @param message a String message associated with the Exception.
     * @param cause   a Throwable cause associated with the Exception.
     */
    public SpreadsheetException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
