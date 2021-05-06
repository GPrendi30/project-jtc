package com.computation.ast;
import com.computation.instruction.INEG;
import com.computation.program.*;

/**
 * An integer negation (e.g., -5, or -x).
 */
public class Negation extends Node {
    
    private final Node child;
    
    
    /**
     * Create a new Negation node.
     * @param child the operand we will negate
     */
    public Negation(final Node child) {
        super();
        this.child = child;
    }

    @Override
    public Type getType() {
        return child.getType();
    }
    
    @Override
    public boolean isConstant() {
        return child.isConstant();
    }
    
    @Override
    public void compile(final Program p) {
        child.compile(p);
        p.append(new INEG());
    }

    @Override
    public String toString() {
        return "(-" + child.toString() + ")";
    }
    
}
