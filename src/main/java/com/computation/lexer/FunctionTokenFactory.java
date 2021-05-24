package com.computation.lexer;

public class FunctionTokenFactory extends StringTokenFactory {

    private final TokenType tokenType;


    /**
     * A Factory for fuctions
     * @param function a String representing a function keyword.
     */
    public FunctionTokenFactory(final String function) {
        super(function);
        this.tokenType = TokenType.FUNCTION;
    }

    @Override
    public Token getToken() {
        // return a token of this object's TokenType
        // with its text and starting position
        return new Token(tokenType, getTokenText(), getTokenStartPosition());
    }
}
