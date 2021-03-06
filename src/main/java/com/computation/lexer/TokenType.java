package com.computation.lexer;

/**
 * A program in a programming language is made up 
 * of different kinds of tokens.
 * This enumeration represents these different kinds.
 *
 * @author Di Pietro Enrico, Prendi Gerald.
 *
 */
public enum TokenType {

    IDENTIFIER("identifier"),

    INTLITERAL("intLiteral"),
    DOUBLELITERAL("doubleLiteral"),
    PLUS("plus"),
    MINUS("minus"),
    STAR("star"),
    SLASH("slash"),
    PERCENT("percent"),
    COLON("colon"),
    FUNCTION("function"),
    OPEN_PAREN("open parenthesis"),
    CLOSED_PAREN("closed parenthesis"),
    COMMA(","),
    END_OF_FILE("end of file");


    private final String name;


    /**
     * Initialize a TokenType.
     *
     * @param name The human-readable name of this TokenType.
     */
    private TokenType(final String name) {
        this.name = name;
    }

    private String getName() {
        return name;
    }
}
