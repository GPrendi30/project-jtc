package com.computation.program;

import com.computation.ast.Node;
import com.computation.ast.NodeException;

/**
 * A Compiler converts an AST into a compiled Program.
 *
 * @author Di Pietro Enrico, Prendi Gerald.
 *
 */
public class Compiler {
    
    /**
     * Compile the given AST.
     * @param node The root of an AST
     * @return the compiled program
     * @throws CompilerException a compiler exception, in case a node can't compile
     */
    public Program compile(final Node node) throws CompilerException {
        final Program p = new Program();

        try {
            node.compile(p);
        } catch (NodeException exception) {
            throw new CompilerException(exception.getMessage(), exception);
        }

        return p;
    }
    
}
