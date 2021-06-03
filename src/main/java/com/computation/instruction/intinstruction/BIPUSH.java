package com.computation.instruction.intinstruction;

import com.computation.instruction.Instruction;
import com.computation.program.Storage;

/**
 * BIPUSH pushes the given value onto the operand stack.
 *
 * @author Di Pietro Enrico, Prendi Gerald.
 *
 */
public class BIPUSH extends Instruction {
    
    private final int value;
    
    /**
     * Creates a new BIPUSH node.
     * @param value a int
     */
    public BIPUSH(final int value) {
        super();
        this.value = value;
    }
    
    @Override
    public void execute(final Storage storage) {
        storage.getOperandStack().ipush(value);
    }
    
    @Override
    public String toString() {
        return "BIPUSH " + value;
    }

}
