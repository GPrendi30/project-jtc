package com.computation.instruction;
import com.computation.program.*;
public class IDIV extends Instruction {

    @Override
    public void execute(final Storage storage) {
        final OperandStack stack = storage.getOperandStack();
        stack.push(stack.pop() / stack.pop());
    }
    
    @Override
    public String toString() {
        return "IDIV";
    }

}
