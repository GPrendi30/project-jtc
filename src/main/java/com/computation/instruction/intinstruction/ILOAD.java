package com.computation.instruction.intinstruction;

import com.computation.instruction.Instruction;
import com.computation.instruction.InstructionException;
import com.computation.program.OperandStack;
import com.computation.program.Storage;
import com.computation.program.VariableTable;
import com.computation.program.VariableTableException;

public class ILOAD extends Instruction {

    private final String var;

    /**
     * Creates a new ILOAD Instruction.
     * @param var a String
     */
    public ILOAD(final String var) {
        super();
        this.var = var;
    }

    @Override
    public void execute(final Storage storage) throws InstructionException {
        final OperandStack stack = storage.getOperandStack();
        final VariableTable variableTable = storage.getVariableTable();
        final int xValue;
        try {
            xValue = variableTable.getInt(var);
        } catch (VariableTableException exception) {
            throw new InstructionException(exception.getMessage(), exception);
        }
        stack.ipush(xValue);
    }
    
    /**
     * Returns a String representing the node.
     * @return a String representing the node.
     */
    public String toString() {
        return "ILOAD " + var;
    }
}
