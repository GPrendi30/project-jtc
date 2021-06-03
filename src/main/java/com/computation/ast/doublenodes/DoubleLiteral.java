package com.computation.ast.doublenodes;

import com.computation.ast.NodeLiteral;
import com.computation.ast.Type;
import com.computation.instruction.Instruction;
import com.computation.instruction.doubleinstruction.BDPUSH;

/**
 * A Literal is an AST node that 
 * corresponds to a literal integer doubleValue
 * (a number in the source code).
 *
 * @author Prendi Gerald.
 *
 */
public class DoubleLiteral extends NodeLiteral {
    
    /**
     * Creates a new literal with a given doubleValue.
     * @param doubleValue a double vaulue for DoubleLiteral
     */
    public DoubleLiteral(final double doubleValue) {
        super(doubleValue);
    }

    @Override
    public Type getType() {
        return Type.DOUBLE;
    }
    
    @Override
    public Instruction instruction() {
        return new BDPUSH(value.doubleValue());
    }   
    
}
