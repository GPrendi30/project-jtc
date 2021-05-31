package com.computation.ast.function;

import com.computation.ast.Type;
import com.computation.ast.range.ArrayNode;
import com.computation.program.Program;

public class SumRange extends FunctionWithRanges {

    public SumRange(String name, FunctionOperation fop, Type returnType) {
        super(name, fop, returnType);
    }

    @Override
    public void compile(Program p) throws Exception {
        final ArrayNode a = (ArrayNode) parameters.get(0);
        for (int i = 0; i < a.getLength(); i++) {
            a.getElement(i).compile(p);
            if (i > 0) {
                p.append(getFunctionOperation());
            }
        }
    }

    @Override
    public Function copy() {
        return new SumRange(this.name, this.fop, this.returnType);
    }
}