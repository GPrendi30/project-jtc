package com.computation.ast.function;

import com.computation.ast.Node;
import com.computation.ast.NodeException;
import com.computation.ast.Type;
import com.computation.program.Program;

import java.util.ArrayList;

public class Function extends Node implements FunctionPrototype {

    public static final int NO_LIMIT = -99;

    public static final int NULLARY = 0;
    public static final int UNARY = 1;
    public static final int BINARY = 2;
    public static final int TERTIARY = 3;


    protected final String name;
    protected final Type[] argumentTypes;
    protected final ArrayList<Node> parameters;
    protected final FunctionOperation fop;
    private final int mode;
    private final int numArguments;
    protected final Type returnType;

    /**
     * Creates a new Function based on the name and Operation.
     * @param name a String name for the function.
     * @param fop the function Operation.
     * @param mode the mode of a Function : Unary, Binary, Tertiary.
     * @param argNum the number of the arguments, int or NO_LIMIT.
     * @param argumentTypes the usable types of the arguments.
     * @param returnType the node TYPE that the function returns.
     */
    public Function(final String name,
                    final FunctionOperation fop,
                    final int mode,
                    final int argNum,
                    final Type[] argumentTypes,
                    final Type returnType) {
        super();
        this.name = name;
        parameters = new ArrayList<>();
        this.fop = fop;
        this.mode = mode;
        this.numArguments = argNum;
        this.argumentTypes = argumentTypes.clone(); // shallow copy, argumentTypes has simple data.
        this.returnType = returnType;
    }

    /**
     * Creates a new Function based on the name and Operation.
     * @param name a String name for the function.
     * @param fop the function Operation.
     * @param mode the mode of a Function : Unary, Binary, Tertiary.
     * @param argumentTypes the usable types of the arguments.
     * @param returnType the node TYPE that the function returns.
     */
    public Function(final String name,
                    final FunctionOperation fop,
                    final int mode,
                    final Type[] argumentTypes,
                    final Type returnType) {
        this(name,
            fop,
            mode,
            mode,// set argnum the same as mode.
            argumentTypes,
            returnType);
    }

    /**
     * Adds an argument to the function.
     * @param arg an argument node.
     * @throws FunctionException a Function Exception.
     */
    public void addParameter(final Node arg) throws FunctionException {
        if (numArguments != Function.NO_LIMIT && parameters.size() >= numArguments) {
            throw new FunctionException("Number of arguments exceeded");
        }

        checkType(arg.getType());
        parameters.add(arg);
    }

    private void checkType(final Type type) throws FunctionException {
        for (final Type t : argumentTypes) {
            if (t.equalsType(type)) {
                return;

            }
        }
        throw new FunctionException("Argument type mismatch");
    }

    @Override
    public Type getType() {
        return returnType;
    }

    @Override
    public boolean isConstant() {
        for (final Node arg : parameters) {
            if (!arg.isConstant()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void compile(final Program p) throws NodeException {
        if (parameters.size() != numArguments && parameters.size() < mode) {
            throw numArguments == Function.NO_LIMIT
                    ? new FunctionException("Mismatch of parameters, needed at least "
                            + mode + " parameters.")
                    : new FunctionException("Mismatch of parameters, needed "
                            + mode + " parameters.");
        }

        int argNum = 0;
        for (final Node arg : parameters) {
            arg.compile(p);

            argNum++;

            if (argNum >= this.mode) {
                callInstruction(p);
            }

        }
    }

    private void callInstruction(final Program p) {
        p.append(functionOperation());
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
        for (final Node arg : parameters) {
            str.append(arg);
            if (parameters.size() > 1) {
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
    public void removeAllArguments() {
        parameters.removeAll(parameters);
    }

    @Override
    public Function copy() {
        return new Function(this.name,
                this.fop,
                this.mode,
                this.numArguments,
                this.argumentTypes,
                this.returnType);
    }
}
