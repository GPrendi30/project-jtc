package com.computation.parser;

import com.computation.ast.Node;
import com.computation.lexer.LexerException;

/**
 * A Parser can convert source code into an AST
 * consisting of Node objects.
 */
public interface Parser {

    /**
     * Parse the given source code.
     * @param sourceCode The source code of the program
     * @return the AST of the program
     * @throws ArithException a Parser Exception.
     * @throws LexerException a Lexer Exception.
     */
    abstract Node parse(String sourceCode) throws ArithException, LexerException;
    
}
