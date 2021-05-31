package com.computation.parser;

public class ArithException extends Exception {

    /**
     * Creates an ArithException.
     */
    public ArithException() {
    }

    /**
     * Creates an ArithException with a message.
     * @param message a String message associated with the Exception.
     */
    public ArithException(String message) {
        super(message);
    }

    /**
     * Creates an ArithException with a message and a cause.
     * @param message a String message associated with the Exception.
     * @param cause a Throwable cause associated with the Exception.
     */
    public ArithException(String message, Throwable cause) {
        super(message, cause);
    }
}