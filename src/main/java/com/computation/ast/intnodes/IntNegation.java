package com.computation.ast.intnodes;

import com.computation.ast.Node;
import com.computation.instruction.Instruction;
import com.computation.instruction.intinstruction.INEG;

/**
 * A negation of a node.
 * IntNegation class (e.g., -5, or -x).
 *
 * @author Prendi Gerald.
 *
 */
public class IntNegation extends IntUnaryNode {
        
    /**
     * Create a new IntNegation node.
     * @param child the operand we will negate
     */
    public IntNegation(final Node child) {
        super(child);
    }
    
    @Override
    public Instruction instruction() {
        return new INEG();
    }
    
}
