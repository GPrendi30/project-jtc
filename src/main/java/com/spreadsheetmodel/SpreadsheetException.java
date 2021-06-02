package com.spreadsheetmodel;

public class SpreadsheetException extends Exception {

    /**
     * Creates an ArithException.
     */
    public SpreadsheetException() {
        super();
    }

    /**
     * Creates an ArithException with a message.
     *
     * @param message a String message associated with the Exception.
     */
    public SpreadsheetException(final String message) {
        super(message);
    }

    /**
     * Creates an ArithException with a message and a cause.
     *
     * @param message a String message associated with the Exception.
     * @param cause   a Throwable cause associated with the Exception.
     */
    public SpreadsheetException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
