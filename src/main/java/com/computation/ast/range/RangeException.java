package com.computation.ast.range;

import com.computation.ast.NodeException;

/**
 * The exceptions for the class Range.
 *
 * @author Prendi Gerald.
 *
 */

public class RangeException extends NodeException {

    /**
     * Creates a new RangeException.
     */
    public RangeException() {
        super();
    }

    /**
     * Creates a RangeException with a message.
     * @param message a message thrown together with an Exception.
     */
    public RangeException(final String message) {
        super(message);
    }

    /**
     * Creates a RangeException with a message and a cause.
     * @param message a String message thrown with the exception.
     * @param cause the cause of the Exception.
     */
    public RangeException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
