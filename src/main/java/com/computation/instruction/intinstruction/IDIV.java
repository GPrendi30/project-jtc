package com.computation.instruction.intinstruction;

import com.computation.instruction.Instruction;
import com.computation.program.OperandStack;
import com.computation.program.Storage;

public class IDIV extends Instruction {
    
    @Override
    public void execute(final Storage storage) {
        final OperandStack stack = storage.getOperandStack();
        final int a = stack.ipop();
        final int b = stack.ipop();
        stack.ipush(b / a);
    }

    @Override
    public String toString() {
        return "IDIV";
    }
    
}
