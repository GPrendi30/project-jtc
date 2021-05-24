package com.computation.ast.function;

import com.computation.ast.Node;
import com.computation.ast.Type;
import com.computation.program.Program;

import java.util.ArrayList;

public class Function extends Node implements FunctionPrototype {

    public static final int NO_LIMIT = -99;

    public static final int NULLARY = 0;
    public static final int UNARY = 1;
    public static final int BINARY = 2;
    public static final int TERTIARY = 3;


    private final String name;
    private final ArrayList<Node> arguments;
    private final FunctionOperation fop;
    private final int mode;
    private final int numArguments;



    /**
     * Creates a new Function based on the name and Operation.
     * @param name a String name for the function.
     * @param fop the function Operation.
     * @param mode the mode of a Function : Unary, Binary, Tertiary.
     * @param argNum the number of the arguments, int or NO_LIMIT.
     */
    public Function(final String name,
                    final FunctionOperation fop,
                    final int mode,
                    final int argNum) {
        super();
        this.name = name;
        arguments = new ArrayList<>();
        this.fop = fop;
        this.mode = mode;
        this.numArguments = argNum;
    }

    /**
     * Creates a new Function based on the name and Operation.
     * @param name a String name for the function.
     * @param fop the function Operation.
     * @param mode the mode of a Function : Unary, Binary, Tertiary.
     */
    public Function(final String name,
                    final FunctionOperation fop,
                    final int mode) {
        super();
        this.name = name;
        arguments = new ArrayList<>();
        this.fop = fop;
        this.mode = mode;
        this.numArguments = mode;
    }

    /**
     * Adds an argument to the function.
     * @param arg an argument node.
     * @throws Exception a Function Exception.
     */
    public void addArgument(final Node arg) throws Exception {
        if (numArguments != Function.NO_LIMIT && arguments.size() >= numArguments) {
            throw new Exception("Number of arguments exceeded");
        }
        arguments.add(arg);
    }

    @Override
    public Type getType() {
        for (final Node arg : arguments) {
            if (arg.getType() == Type.DOUBLE) {
                return Type.DOUBLE;
            }
        }

        return Type.INT;
    }

    @Override
    public boolean isConstant() {
        for (final Node arg : arguments) {
            if (!arg.isConstant()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void compile(final Program p) throws Exception {
        // TODO make FunctionException
        if (arguments.size() - 1 != numArguments && arguments.size() < mode) {
            throw numArguments == Function.NO_LIMIT
                    ? new Exception("Mismatch of parameters, needed at least "
                            + mode + " parameters.")
                    : new Exception("Mismatch of parameters, needed "
                            + mode + " parameters.");
        }

        int argNum = 0;
        for (final Node arg : arguments) {
            arg.compile(p);

            argNum++;

            if (argNum >= this.mode) {
                p.append(functionOperation());
            }

        }
    }

    //to be overwritten
    private FunctionOperation functionOperation() {
        return fop;
    }


    @Override
    public String toString() {
        // to be implemented in subclasses
        final StringBuilder str = new StringBuilder();
        str.append(name + "(");
        for (final Node arg : arguments) {
            str.append(arg);
            if (arguments.size() > 1) {
                str.append(",");
            }
        }
        if (str.lastIndexOf(",") == str.length() - 1) {
            str.delete(str.length() - 1,str.length());
        }
        str.append(")");
        return str.toString();
    }

    /**
     * Get the functionOperation(what the function does)
     * @return the FunctionOperation of a function.
     */
    public FunctionOperation getFunctionOperation() {
        return fop;
    }

    /**
     * Get the name of the function.
     * @return a String name of a function.
     */
    public String getName() {
        return name;
    }

    /**
     * Removes all the arguments of a function.
     * Deprecated , but might be usefull.
     */
    @Deprecated
    public void removeAllArguments() {
        arguments.removeAll(arguments);
    }

    @Override
    public Function copy() {
        return new Function(this.name,
                this.fop,
                this.mode,
                this.numArguments);
    }
}
