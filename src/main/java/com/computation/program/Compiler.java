package com.computation.program;

import com.computation.ast.Node;

/**
 * A Compiler converts an AST into a compiled Program.
 */
public class Compiler {
    
    /**
     * Compile the given AST.
     * @param node The root of an AST
     * @return the compiled program
     * @throws Exception a Compiler Exception.
     */
    public Program compile(final Node node) throws Exception {
        final Program p = new Program();
        node.compile(p);
        return p;
    }
    
}
