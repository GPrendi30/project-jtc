package com.computation.instruction.intinstruction;

import com.computation.instruction.Instruction;
import com.computation.program.OperandStack;
import com.computation.program.Storage;

/**
 * ISUB subtracts the top value from the second-to-top value
 * of the OperandStack,
 * and ipushes the result back to the OperandStack.
 *
 * @author Di Pietro Enrico, Prendi Gerald.
 *
 */
public class ISUB extends Instruction {

    @Override
    public void execute(final Storage storage) {
        final OperandStack stack = storage.getOperandStack();
        stack.ipush(-stack.ipop() + stack.ipop());
    }
        
    @Override
    public String toString() {
        return "ISUB";
    }

}
