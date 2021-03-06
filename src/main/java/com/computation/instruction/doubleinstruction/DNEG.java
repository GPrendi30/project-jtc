package com.computation.instruction.doubleinstruction;

import com.computation.instruction.Instruction;
import com.computation.program.OperandStack;
import com.computation.program.Storage;

/**
 * DNEG negates a Double.
 *
 * @author Di Pietro Enrico, Prendi Gerald.
 *
 */
public class DNEG extends Instruction {

    @Override
    public void execute(final Storage storage) {
        final OperandStack stack = storage.getOperandStack();
        stack.dpush(-stack.dpop());
    }

    @Override
    public String toString() {
        return "DNEG";
    }
}
