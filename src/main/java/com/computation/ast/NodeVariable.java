package com.computation.ast;

import com.computation.instruction.Instruction;
import com.computation.program.Program;

public class NodeVariable extends Node {

    protected final String varName;
    
    /**
     * Creates a new variable with a given doubleVarName.
     * @param varName a String
     */
    public NodeVariable(final String varName) {
        super();
        this.varName = varName;
    }

    @Override
    public Type getType() {
        return Type.INVALID;
    }

    @Override
    public boolean isConstant() {
        return false;
    }
    
    @Override
    public void compile(final Program p) {
        p.append(instruction());
    }

    /**
     * Returns the JVM instruction of the Node.
     * @return the JVM instruction of the Node.
     */
    public Instruction instruction() {
        return null;
    }

    @Override
    public String toString() {
        return this.varName;
    }
}
