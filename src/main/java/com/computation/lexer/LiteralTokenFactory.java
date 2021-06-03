package com.computation.lexer;

/**
 * A special kind of RegExTokenFactory that produces tokens
 * that are integer literals.
 *
 * @author Di Pietro Enrico, Prendi Gerald.
 *
 */
public class LiteralTokenFactory extends RegExTokenFactory {

    /**
     * Create a new LiteralTokenFactory.
     */
    public LiteralTokenFactory() {
        // regular expression for an integer literal
        super("[0-9]*\\.?[0-9]+");
    }

    @Override
    public Token getToken() {
        // return a token of the appropriate TokenType 
        // with its text and starting position

        final String tokenContent = getTokenText();
        final int position = getTokenStartPosition();
        return  tokenContent.contains(".")
                ?  new Token(TokenType.DOUBLELITERAL, tokenContent, position)
                :  new Token(TokenType.INTLITERAL, tokenContent, position);
    }

}
