package com.computation.instruction;
import com.computation.program.*;

public class ILOAD extends Instruction {

    private final String var;
    
    /**
     * Creates a new ILOAD Instruction.
     * @param var a Variable
     */
    public ILOAD(final String var) {
        super();
        this.var = var;
    }
    
    @Override
    public void execute(final Storage storage) {
        final OperandStack stack = storage.getOperandStack();
        final VariableTable variableTable = storage.getVariableTable();
        final int xValue = variableTable.get(var);
        stack.push(xValue);
    }
    
    @Override
    public String toString() {
        return "ILOAD " + var;
    }
}
