package com.computation.instruction;

import com.computation.program.Storage;

/**
 * An IJVM-like Instruction.
 */
public class Instruction {
    
    /**
     * Execute this Instruction.
     * @param storage The "memory" to use during the execution
     * @throws InstructionException if it can't execute the instruction.
     */
    public void execute(final Storage storage) throws InstructionException {
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
