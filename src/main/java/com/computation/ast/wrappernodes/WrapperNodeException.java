package com.computation.ast.wrappernodes;

import com.computation.ast.NodeException;

public class WrapperNodeException extends NodeException {

    /**
     * Creates a new WrapperNodeException.
     */
    public WrapperNodeException() {
        super();
    }

    /**
     * Creates a WrapperNodeException with a message.
     * @param message a message thrown together with an Exception.
     */
    public WrapperNodeException(final String message) {
        super(message);
    }

    /**
     * Creates a WrapperNodeException with a message and a cause.
     * @param message a String message thrown with the exception.
     * @param cause the cause of the Exception.
     */
    public WrapperNodeException(final String message, final Throwable cause) {
        super(message, cause);
    }
}