package com.computation.instruction.intinstruction;

import com.computation.instruction.Instruction;
import com.computation.instruction.InstructionException;
import com.computation.program.OperandStack;
import com.computation.program.Storage;

/**
 * IDIV is a division of Integers.
 *
 * @author Di Pietro Enrico, Prendi Gerald.
 *
 */

public class IDIV extends Instruction {
    
    @Override
    public void execute(final Storage storage) throws InstructionException {
        final OperandStack stack = storage.getOperandStack();
        final int a = stack.ipop();
        final int b = stack.ipop();
        if (a == 0) {
            throw new InstructionException("Division by 0");
        }
        stack.ipush(b / a);
    }

    @Override
    public String toString() {
        return "IDIV";
    }
    
}
