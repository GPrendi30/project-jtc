package com.computation.ast.function;

import com.computation.ast.NodeException;
import com.computation.ast.Type;
import com.computation.ast.range.ArrayNode;
import com.computation.program.Program;

/**
 * A function that sums a range.
 *
 * @author Di Pietro Enrico, Prendi Gerald.
 *
 */

public class SumRange extends FunctionWithRanges {

    /**
     * A function that sums a range.
     * @param name the name
     * @param fop the operation.
     * @param returnType the return type.
     */
    public SumRange(final String name,
                    final FunctionOperation fop,
                    final Type returnType) {
        super(name, fop, returnType);
    }

    @Override
    public void compile(final Program p) throws NodeException {
        final ArrayNode a = (ArrayNode) parameters.get(0);
        for (int i = 0; i < a.getLength(); i++) {
            a.getElement(i).compile(p);
            if (i > 0) {
                p.append(getFunctionOperation());
            }
        }
    }

    @Override
    public Function copy() {
        return new SumRange(this.name, this.fop, this.returnType);
    }
}