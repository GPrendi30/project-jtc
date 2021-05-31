package com.computation.ast.function;

import com.computation.ast.Type;

public class BinaryFunction extends Function {

    public BinaryFunction(final String name,
                          final FunctionOperation fop,
                          final Type returnType) {
        super(name,
                fop,
                Function.BINARY,
                2,
                new Type[]
                        {Type.DOUBLE, Type.INT},
                returnType);

    }

    public BinaryFunction(final String name,
                          final FunctionOperation fop,
                          final int argNum,
                          final Type returnType) {
        super(name,
                fop,
                Function.BINARY,
                argNum,
                new Type[]
                        {Type.DOUBLE, Type.INT},
                returnType);

    }

}
