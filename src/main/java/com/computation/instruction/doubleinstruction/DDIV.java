package com.computation.instruction.doubleinstruction;

import com.computation.instruction.Instruction;
import com.computation.program.OperandStack;
import com.computation.program.Storage;

public class DDIV extends Instruction {

    @Override
    public void execute(final Storage storage) {
        final OperandStack stack = storage.getOperandStack();
        final double a = stack.dpop();
        final double b = stack.dpop();
        stack.dpush(b/a);
    }

    @Override
    public String toString() {
        return "DDIV";
    }
    
}
