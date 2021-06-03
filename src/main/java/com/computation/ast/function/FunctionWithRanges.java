package com.computation.ast.function;

import com.computation.ast.Type;

/**
 * A function working with ranges.
 *
 * @author Di Pietro Enrico, Prendi Gerald.
 *
 */

public class FunctionWithRanges extends Function {

    /**
     * Creates a FunctionWithRanges.
     * @param name the name of the function.
     * @param fop the operation of the function.
     * @param returnType the return type.
     */
    public FunctionWithRanges(final String name,
                              final FunctionOperation fop,
                              final Type returnType) {
        super(name, fop,
                UNARY,
                1,
                new Type[]
                    {Type.ARRAY},
                returnType);
    }


    @Override
    public Function copy() {
        return new FunctionWithRanges(this.name,
                                    this.fop,
                                    this.returnType);
    }
}
