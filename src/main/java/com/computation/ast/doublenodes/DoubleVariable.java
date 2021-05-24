package com.computation.ast.doublenodes;

import com.computation.ast.NodeVariable;
import com.computation.ast.Type;
import com.computation.instruction.Instruction;
import com.computation.instruction.doubleinstruction.DLOAD;

public class DoubleVariable extends NodeVariable {

    /**
     * Creates a new variable with a given doubleVarName.
     * @param doubleVarName a String
     */
    public DoubleVariable(final String doubleVarName) {
        super(doubleVarName);
    }

    @Override
    public Type getType() {
        return Type.DOUBLE;
    }
    
    @Override
    public Instruction instruction() {
        return new DLOAD(varName);
    }   
}
