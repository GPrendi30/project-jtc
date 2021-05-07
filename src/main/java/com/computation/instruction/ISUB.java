package com.computation.instruction;

import com.computation.program.OperandStack;
import com.computation.program.Storage;

/**
 * ISUB subtracts the top value from the second-to-top value
 * of the OperandStack,
 * and pushes the result back to the OperandStack.
 */
public class ISUB extends Instruction {
    
    @Override
    public void execute(final Storage storage) {
        final OperandStack stack = storage.getOperandStack();
        stack.push(stack.pop() - stack.pop());
    }
        
    @Override
    public String toString() {
        return "ISUB";
    }

}
