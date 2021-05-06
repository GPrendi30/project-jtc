package com.computation.ast;
import com.computation.instruction.ILOAD;
import com.computation.program.*;

public class Variable extends Node {
    
    private final String name;
    
    /**
     * Creates a new variable with a given name.
     * @param name a String
     */
    public Variable(final String name) {
        super();
        this.name = name;
    }

    @Override
    public Type getType() {
        return Type.INT;
    }

    @Override
    public boolean isConstant() {
        return false;
    }
    
    @Override
    public void compile(final Program p) {
        p.append(new ILOAD(name));
    }

    @Override
    public String toString() {
        return this.name;
    }
    
}
