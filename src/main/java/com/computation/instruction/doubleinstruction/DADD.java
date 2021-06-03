package com.computation.instruction.doubleinstruction;

import com.computation.instruction.Instruction;
import com.computation.program.OperandStack;
import com.computation.program.Storage;

/**
 * DADD is an addition of Doubles.
 *
 * @author Di Pietro Enrico, Prendi Gerald.
 *
 */
public class DADD extends Instruction {
    
    @Override
    public void execute(final Storage storage) {
        final OperandStack stack = storage.getOperandStack();
        stack.dpush(stack.dpop() + stack.dpop());
    }

    @Override
    public String toString() {
        return "DADD";
    }    
}
