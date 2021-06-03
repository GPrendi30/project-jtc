package com.computation.instruction.doubleinstruction;

import com.computation.instruction.Instruction;
import com.computation.program.Storage;

/**
 * BIPUSH pushes the given value onto the operand stack.
 *
 * @author Di Pietro Enrico, Prendi Gerald.
 *
 */
public class BDPUSH extends Instruction {

    private final double value;

    /**
     * Creates a new BIPUSH node.
     *
     * @param value a int
     */
    public BDPUSH(final double value) {
        super();
        this.value = value;
    }

    @Override
    public void execute(final Storage storage) {
        storage.getOperandStack().dpush(value);
    }

    @Override
    public String toString() {
        return "BDPUSH " + value;
    }

}
