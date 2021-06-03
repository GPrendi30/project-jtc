package com.computation.program;

public class CompilerException extends Exception {

    /**
     * Creates an CompilerException.
     */
    public CompilerException() {
        super();
    }

    /**
     * Creates an CompilerException with a message.
     *
     * @param message a String message associated with the Exception.
     */
    public CompilerException(final String message) {
        super(message);
    }

    /**
     * Creates an CompilerException with a message and a cause.
     *
     * @param message a String message associated with the Exception.
     * @param cause   a Throwable cause associated with the Exception.
     */
    public CompilerException(final String message, final Throwable cause) {
        super(message, cause);
    }
}