package com.computation.ast.function;

import com.computation.ast.NodeException;

public class FunctionException extends NodeException {

    /**
     * Creates a new FunctionException.
     */
    public FunctionException() {
        super();
    }

    /**
     * Creates a FunctionException with a message.
     * @param message a message thrown together with an Exception.
     */
    public FunctionException(final String message) {
        super(message);
    }

    /**
     * Creates a FunctionException with a message and a cause.
     * @param message a String message thrown with the exception.
     * @param cause the cause of the Exception.
     */
    public FunctionException(final String message, final Throwable cause) {
        super(message, cause);
    }
}