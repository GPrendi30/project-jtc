package com.computation.ast.doublenodes;

import com.computation.ast.Node;
import com.computation.instruction.Instruction;
import com.computation.instruction.doubleinstruction.DNEG;

/**
 * A negation of a node.
 * DoubleNegation class (e.g., -5.0, or -x).
 *
 * @author Prendi Gerald.
 *
 */
public class DoubleNegation extends DoubleUnaryNode {
        
    /**
     * Create a new IntNegation node.
     * @param child the operand we will negate
     */
    public DoubleNegation(final Node child) {
        super(child);
    }
    
    @Override
    public Instruction instruction() {
        return new DNEG();
    }
    
}
