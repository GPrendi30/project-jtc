package com.computation.instruction.intinstruction;

import com.computation.instruction.Instruction;
import com.computation.program.OperandStack;
import com.computation.program.Storage;

/**
 * I2D casts an Integer into a Double.
 *
 * @author Di Pietro Enrico, Prendi Gerald.
 *
 */
public class I2D extends Instruction {
    
    @Override
    public void execute(final Storage storage) {
        final OperandStack stack = storage.getOperandStack();
        final int val = stack.ipop();
        stack.dpush(val);
    }
    
    @Override
    public String toString() {
        return "I2D";
    }
}
