package com.computation.ast.intnodes;

import com.computation.ast.NodeLiteral;
import com.computation.ast.Type;
import com.computation.instruction.Instruction;
import com.computation.instruction.intinstruction.BIPUSH;


/**
 * A Literal is an AST node that 
 * corresponds to a literal integer intValue
 * (a number in the source code).
 *
 * @author Prendi Gerald.
 *
 */
public class IntLiteral extends NodeLiteral {
    
    /**
     * Creates a new variable with a given doubleVarName.
     * @param intValue an Integer
     */
    public IntLiteral(final int intValue) {
        super(intValue);
    }

    @Override
    public Type getType() {
        return Type.INT;
    }
    
    @Override
    public Instruction instruction() {
        return new BIPUSH(value.intValue());
    }   
}
