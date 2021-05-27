package com.computation.ast.function;

import com.computation.ast.Node;

interface FunctionPrototype {

    public abstract void addParameter(final Node arg) throws Exception;

    public abstract Function copy();

}
