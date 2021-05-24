package com.computation.ast.intnodes;

import com.computation.ast.NodeVariable;
import com.computation.ast.Type;
import com.computation.instruction.Instruction;
import com.computation.instruction.intinstruction.ILOAD;

public class IntVariable extends NodeVariable {

    /**
     * Creates a new variable with a given doubleVarName.
     * @param intVarName a String
     */
    public IntVariable(final String intVarName) {
        super(intVarName);
    }

    @Override
    public Type getType() {
        return Type.INT;
    }
    
    @Override
    public Instruction instruction() {
        return new ILOAD(varName);
    }   
}
