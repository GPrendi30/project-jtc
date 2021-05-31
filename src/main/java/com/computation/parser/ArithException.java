package com.computation.parser;

public class ArithException extends Exception {

    public ArithException() {
    }

    public ArithException(String message) {
        super(message);
    }

    public ArithException(String message, Throwable cause) {
        super(message, cause);
    }
}