package com.computation.ast.function;

import com.computation.instruction.Instruction;
import com.computation.program.Storage;

/**
 * The fundamentals operations of a function.
 *
 * @author Di Pietro Enrico, Prendi Gerald.
 *
 */

public abstract class FunctionOperation extends Instruction {

    /**
     * executes a Function Operation.
     * @param storage The "memory" to use during the execution
     */
    public abstract void execute(final Storage storage);

    /**
     * Returns a String representation of a FunctionOperation.
     * @return a String representation.
     */
    public abstract String toString();
}
