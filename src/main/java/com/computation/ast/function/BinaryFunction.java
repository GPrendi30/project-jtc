package com.computation.ast.function;

import com.computation.ast.Type;

public class BinaryFunction extends Function {

    /**
     * Creates a Binary Function, a function that takes 2 arguments.
     * @param name the name of the function.
     * @param fop the instruction of the function.
     * @param returnType the return type, Type.INT, Type.DOUBLE
     */
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

    /**
     * Creates a Binary Function.
     * @param name the name of the function.
     * @param fop the instruction of the function.
     * @param argNum the number of arguments.
     * @param returnType the return type, Type.INT, Type.DOUBLE
     */
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
