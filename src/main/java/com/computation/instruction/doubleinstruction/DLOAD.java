package com.computation.instruction.doubleinstruction;

import com.computation.instruction.Instruction;
import com.computation.program.OperandStack;
import com.computation.program.Storage;
import com.computation.program.VariableTable;

public class DLOAD extends Instruction {

    private final String var;

    /**
     * Creates a new ILOAD Instruction.
     * @param var a String
     */
    public DLOAD(final String var) {
        super();
        this.var = var;
    }
    
    @Override
    public void execute(final Storage storage) {
        final OperandStack stack = storage.getOperandStack();
        final VariableTable variableTable = storage.getVariableTable();
        final double xValue = variableTable.getDouble(var);
        stack.dpush(xValue);
    }

    /**
     * Returns a String.
     * @return a String representation of the Node
     */
    public String toString() {
        return "DLOAD " + var;
    }
    
}
