package com.computation.instruction.doubleinstruction;

import com.computation.instruction.Instruction;
import com.computation.instruction.InstructionException;
import com.computation.program.OperandStack;
import com.computation.program.Storage;

/**
 * DDIV is a division of Doubles.
 *
 * @author Di Pietro Enrico, Prendi Gerald.
 *
 */
public class DDIV extends Instruction {

    @Override
    public void execute(final Storage storage) throws InstructionException {
        final OperandStack stack = storage.getOperandStack();
        final double a = stack.dpop();
        final double b = stack.dpop();
        if (a == 0) {
            throw new InstructionException("Division by 0");
        }
        stack.dpush(b / a);
    }

    @Override
    public String toString() {
        return "DDIV";
    }
    
}
