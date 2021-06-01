package com.computation.program;

public class CompilerException extends Exception {

    /**
     * Creates an ArithException.
     */
    public CompilerException() {
        super();
    }

    /**
     * Creates an ArithException with a message.
     *
     * @param message a String message associated with the Exception.
     */
    public CompilerException(final String message) {
        super(message);
    }

    /**
     * Creates an ArithException with a message and a cause.
     *
     * @param message a String message associated with the Exception.
     * @param cause   a Throwable cause associated with the Exception.
     */
    public CompilerException(final String message, final Throwable cause) {
        super(message, cause);
    }
}