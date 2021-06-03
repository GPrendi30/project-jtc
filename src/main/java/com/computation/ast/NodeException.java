package com.computation.ast;

/**
 * The exceptions for the class Range.
 *
 * @author Di Pietro Enrico, Prendi Gerald.
 *
 */

public class NodeException extends Exception {

    /**
     * Creates a new NodeException.
     */
    public NodeException() {
        super();
    }

    /**
     * Creates a NodeException with a message.
     * @param message a message thrown together with an Exception.
     */
    public NodeException(final String message) {
        super(message);
    }

    /**
     * Creates a NodeException with a message and a cause.
     * @param message a String message thrown with the exception.
     * @param cause the cause of the Exception.
     */
    public NodeException(final String message, final Throwable cause) {
        super(message, cause);
    }
}