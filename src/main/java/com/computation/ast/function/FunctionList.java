package com.computation.ast.function;

import com.computation.ast.Type;
import com.computation.ast.range.ArrayNode;
import com.computation.program.OperandStack;
import com.computation.program.Program;
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
                new Type[]
                    { Type.INT, Type.DOUBLE },
                Type.DOUBLE
        )),

    SUM("SUM",
        new BinaryFunction("sum", new FunctionOperation() {
            @Override
            public void execute(final Storage storage) {
                final OperandStack op = storage.getOperandStack();
                op.dpush(op.dpop() + op.dpop());
            }

            @Override
            public String toString() {
                return "sum";
            }
        },
                Function.NO_LIMIT,
                Type.DOUBLE)),


    ABS("ABS",
        new BinaryFunction("abs", new FunctionOperation() {
            @Override
            public void execute(final Storage storage) {
                final OperandStack op = storage.getOperandStack();
                final double a = op.dpop();
                op.dpush(a > 0 ? a : -a);
            }

            @Override
            public String toString() {
                return "abs";
            }
        },

                Type.DOUBLE)),


    AVG("AVG",
        new BinaryFunction("avg", new FunctionOperation() {
            @Override
            public void execute(final Storage storage) {
                final OperandStack op = storage.getOperandStack();
                op.dpush((op.dpop() + op.dpop()) / 2);
            }

            @Override
            public String toString() {
                return "avg";
            }
        },

        Type.DOUBLE)),


    MAX("MAX",
        new BinaryFunction("max", new FunctionOperation() {
            @Override
            public void execute(final Storage storage) {
                final OperandStack op = storage.getOperandStack();
                op.dpush(Math.max(op.dpop(), op.dpop()));
            }

            @Override
            public String toString() {
                return "max";
            }
        },
        Type.DOUBLE)),


    MIN("MIN",
        new BinaryFunction("min", new FunctionOperation() {
            @Override
            public void execute(final Storage storage) {
                final OperandStack op = storage.getOperandStack();
                op.dpush(Math.min(op.dpop(), op.dpop()));
            }

            @Override
            public String toString() {
                return "min";
            }
        },
        Type.DOUBLE)),

    ASUM("ASUM",
            new SumRange("sum_range",
                  SUM.function.getFunctionOperation(),
                    Type.DOUBLE)),



    MOD("MOD",
        new BinaryFunction("mod", new FunctionOperation() {
            @Override
            public void execute(final Storage storage) {
                final OperandStack op = storage.getOperandStack();
                final double a = op.dpop();
                final double b = op.dpop();
                // invert a and b because of the order those are popped
                op.dpush(b % a);
            }

            @Override
            public String toString() {
                return "mod";
            }
        },
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
     * @throws FunctionException throws a FunctionException //TODO implement
     */
    public static Function stringToFunction(final String name) throws FunctionException {
        for (final FunctionList func : FunctionList.values()) {
            if (name.equals(func.getName())) {
                final Function f = func.getFunction();
                return f.copy();
            }
        }
        throw new FunctionException("Function not defined");
    }

}
