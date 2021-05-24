package com.computation.instruction.doubleinstruction;

import com.computation.instruction.Instruction;
import com.computation.program.OperandStack;
import com.computation.program.Storage;

public class D2I extends Instruction {

    @Override
    public void execute(final Storage storage) {
        final OperandStack stack = storage.getOperandStack();
        final double val = stack.ipop();
        stack.ipush((int) val);
    }

    @Override
    public String toString() {
        return "D2I";
    }
    
}
