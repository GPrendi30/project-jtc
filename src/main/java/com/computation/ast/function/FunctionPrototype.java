package com.computation.ast.function;

import com.computation.ast.Node;

/**
 * Some methods to work with a function.
 *
 * @author Di Pietro Enrico, Prendi Gerald.
 *
 */

interface FunctionPrototype {

    public abstract void addParameter(final Node arg) throws FunctionException;

    public abstract Function copy();

}
