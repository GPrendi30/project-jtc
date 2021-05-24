package com.computation.instruction.intinstruction;

import com.computation.instruction.Instruction;
import com.computation.program.OperandStack;
import com.computation.program.Storage;

/**
 * INEG negates the top value from the OperandStack
 * and ipushes the result back to the OperandStack.
 */
public class INEG extends Instruction {

    @Override
    public void execute(final Storage storage) {
        final OperandStack stack = storage.getOperandStack();
        stack.ipush(-stack.ipop());
    }
    
    @Override
    public String toString() {
        return "INEG";
    }

}
