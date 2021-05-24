package com.computation.instruction;

import com.computation.program.Storage;

/**
 * An IJVM-like Instruction.
 */
public class Instruction {
    
    /**
     * Execute this Instruction.
     * @param storage The "memory" to use during the execution
     */
    public void execute(final Storage storage) {
        //to be overriden.
    }
    
    /**
     * Get a String with the disassembled Instruction.
     * @return A String-representation of this Instruction.
     */
    public String toString() {
        return null;
    }
    
}
