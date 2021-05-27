package com.computation.ast.function;

import com.computation.ast.Type;
import com.computation.program.OperandStack;
import com.computation.program.Storage;

public enum FunctionList {

    SIN("SIN",
        new Function("sin",
            new FunctionOperation() {
                @Override
                public void execute(final Storage storage) {
                    final OperandStack op = storage.getOperandStack();
                    final double a = op.dpop();
                    op.dpush(Math.sin(a));
                }

                @Override
                public String toString() {
                    return "sin";
                }
            },
                Function.UNARY,
                new Type[]{Type.INT, Type.DOUBLE},
                Type.DOUBLE
        )),

    COS("COS",
        new Function("cos", new FunctionOperation() {
            @Override
            public void execute(final Storage storage) {
                final OperandStack op = storage.getOperandStack();
                final double a = op.dpop();
                op.dpush(Math.cos(a));
            }

            @Override
            public String toString() {
                return "cos";
            }
        },
                Function.UNARY,
                new Type[]{Type.INT, Type.DOUBLE},
                Type.DOUBLE
        )),

    SUM("SUM",
        new Function("sum", new FunctionOperation() {
            @Override
            public void execute(final Storage storage) {
                final OperandStack op = storage.getOperandStack();
                final double a = op.dpop();
                final double b = op.dpop();
                op.dpush(a + b);
            }

            @Override
            public String toString() {
                return "sum";
            }
        },
                Function.BINARY,
                Function.NO_LIMIT,
                new Type[]{Type.DOUBLE, Type.INT},
                Type.DOUBLE));

    //TODO add more functions

    private final String name;
    private final Function function;


    private FunctionList(final String name, final Function function) {
        this.name = name;
        this.function = function;
    }

    /**
     * Returns the function.
     * @return the function of an enum Type.
     */
    public Function getFunction() {
        return function;
    }

    /**
     * Gets the name of the function.
     * @return the String representation of the name.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns a function if it find a match of name.
     * @param name a string name for a function.
     * @return a new Function, with the same name and functionOperation.
     * @throws Exception throws a FunctionTypeException //TODO implement
     */
    public static Function stringToFunction(final String name) throws Exception {
        for (final FunctionList func : FunctionList.values()) {
            if (name.equals(func.getName())) {
                final Function f = func.getFunction();
                return f.copy();
            }
        }
        throw new Exception("Function not defined");
    }


}
