package com.computation.lexer;

/**
 * A factory for tokens of type identifier.
 *
 * @author Di Pietro Enrico, Prendi Gerald.
 *
 */

public class IdentifierTokenFactory extends RegExTokenFactory {

    /**
     * Create an IdentifierTokenFactory.
     */
    public IdentifierTokenFactory() {
        super("[a-zA-Z_][a-zA-Z_0-9]*");
    }

    /**
     * Produce a token.
     * @return the currently found token
     */
    public Token getToken() {
        return new Token(TokenType.IDENTIFIER, getTokenText(), getTokenStartPosition());
    }
    
}
//---