package com.computation.instruction.doubleinstruction;

import com.computation.instruction.Instruction;
import com.computation.instruction.InstructionException;
import com.computation.program.OperandStack;
import com.computation.program.Storage;
import com.computation.program.VariableTable;
import com.computation.program.VariableTableException;

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
    public void execute(final Storage storage) throws InstructionException {
        final OperandStack stack = storage.getOperandStack();
        final VariableTable variableTable = storage.getVariableTable();
        final double xValue;
        try {
            xValue = variableTable.getDouble(var);
        } catch (VariableTableException exception) {
            throw new InstructionException(exception.getMessage(), exception);
        }
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
