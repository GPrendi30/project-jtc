package com.computation.instruction;

/**
 * The exceptions for the class Instruction.
 *
 * @author Di Pietro Enrico, Prendi Gerald.
 *
 */

public class InstructionException extends Exception {

    /**
     * Creates an InstructionException.
     */
    public InstructionException() {
        super();
    }

    /**
     * Creates an InstructionException with a message.
     *
     * @param message a String message associated with the Exception.
     */
    public InstructionException(final String message) {
        super(message);
    }

    /**
     * Creates an InstructionException with a message and a cause.
     *
     * @param message a String message associated with the Exception.
     * @param cause   a Throwable cause associated with the Exception.
     */
    public InstructionException(final String message, final Throwable cause) {
        super(message, cause);
    }
}