package com.computation.ast.function;

import com.computation.ast.Type;

public class FunctionWithRanges extends Function {

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
        return super.copy();
    }
}
