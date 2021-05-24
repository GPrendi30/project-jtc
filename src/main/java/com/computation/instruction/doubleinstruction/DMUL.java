package com.computation.instruction.doubleinstruction;

import com.computation.instruction.Instruction;
import com.computation.program.OperandStack;
import com.computation.program.Storage;

public class DMUL extends Instruction {


    @Override
    public void execute(final Storage storage) {
        final OperandStack stack = storage.getOperandStack();
        stack.dpush(stack.dpop() * stack.dpop());
    }

    @Override
    public String toString() {
        return "DMUL";
    }
    
}
