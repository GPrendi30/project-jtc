package com.computation.lexer;

public class LexerException extends Exception {

    /**
     * Creates a new LexerException.
     */
    public LexerException() {
        super();
    }

    /**
     * Creates a LexerException with a message.
     * @param message a message thrown together with an Exception.
     */
    public LexerException(final String message) {
        super(message);
    }

    /**
     * Creates a LexerException with a message and a cause.
     * @param message a String message thrown with the exception.
     * @param cause the cause of the Exception.
     */
    public LexerException(final String message, final Throwable cause) {
        super(message, cause);
    }
}