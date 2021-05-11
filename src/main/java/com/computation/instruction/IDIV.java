package com.computation.instruction;

import com.computation.program.OperandStack;
import com.computation.program.Storage;

public class IDIV extends Instruction {

    @Override
    public void execute(final Storage storage) {
        final OperandStack stack = storage.getOperandStack();
        final int l = stack.pop();
        final int r = stack.pop();
        if (r == 0) {
            throw new java.lang.ArithmeticException("/ by zero");
        }
        stack.push(r / l);
    }
    
    @Override
    public String toString() {
        return "IDIV";
    }

}
