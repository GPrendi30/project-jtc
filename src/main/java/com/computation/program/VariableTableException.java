package com.computation.program;

public class VariableTableException extends Exception {
    /**
     * Creates an ArithException.
     */
    public VariableTableException() {
        super();
    }

    /**
     * Creates an ArithException with a message.
     *
     * @param message a String message associated with the Exception.
     */
    public VariableTableException(final String message) {
        super(message);
    }

    /**
     * Creates an ArithException with a message and a cause.
     *
     * @param message a String message associated with the Exception.
     * @param cause   a Throwable cause associated with the Exception.
     */
    public VariableTableException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
