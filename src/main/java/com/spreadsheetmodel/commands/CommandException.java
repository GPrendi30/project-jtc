package com.spreadsheetmodel.commands;

public class CommandException extends Exception {

    /**
     * Creates an CommandException.
     */
    public CommandException() {
        super();
    }

    /**
     * Creates an CommandException with a message.
     *
     * @param message a String message associated with the Exception.
     */
    public CommandException(final String message) {
        super(message);
    }

    /**
     * Creates an CommandException with a message and a cause.
     *
     * @param message a String message associated with the Exception.
     * @param cause   a Throwable cause associated with the Exception.
     */
    public CommandException(final String message, final Throwable cause) {
        super(message, cause);
    }
}