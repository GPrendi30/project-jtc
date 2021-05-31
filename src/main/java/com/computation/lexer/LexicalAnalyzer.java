package com.computation.lexer;

import java.util.Arrays;

/**
 * A LexicalAnalyzer breaks a String into Tokens.
 * 
 * <pre>
 * lexer.fetchNextToken();
 * Token t1 = lexer.getCurrentToken();
 * </pre> 
 */
public final class LexicalAnalyzer {

    private Token token;
    private String text;
    private int position;
    private TokenFactory[] tokenFactories;

    
    /**
     * Create an analyzer for the given text, 
     * using the given factories to recognize and create tokens.
     * @param expression The text to analyze
     * @param factories The token factories to use
     */
    public LexicalAnalyzer(final String expression, final TokenFactory[] factories) {
        tokenFactories = Arrays.copyOf(factories, factories.length);
        setText(expression);
    }

    /**
     * Create an analyzer for the given text.
     * @param expression The text to analyze
     */
    public LexicalAnalyzer(final String expression) {
        this(expression, new TokenFactory[] {
            new FunctionTokenFactory("ISUM"),
            new FunctionTokenFactory("SIN"),
            new FunctionTokenFactory("COS"),
            new FunctionTokenFactory("SUM"),
            new IdentifierTokenFactory(),
            new LiteralTokenFactory(),
            new OperatorTokenFactory(":", TokenType.COLON),
            new OperatorTokenFactory("+", TokenType.PLUS),
            new OperatorTokenFactory("-", TokenType.MINUS),
            new OperatorTokenFactory("*", TokenType.STAR),
            new OperatorTokenFactory("/", TokenType.SLASH),
            new OperatorTokenFactory("%", TokenType.PERCENT),
            new OperatorTokenFactory("(", TokenType.OPEN_PAREN),
            new OperatorTokenFactory(")", TokenType.CLOSED_PAREN),
            new OperatorTokenFactory(",", TokenType.COMMA),
        });
    }

    /**
     * Provide a new text to analyze.
     * @param expression The text to analyze
     */
    public void setText(final String expression) {
        token = null;
        text = expression;
        position = 0;
        for (final TokenFactory factory : tokenFactories) {
            factory.setText(expression);
        }
    }

    /**
     * Ask the analyzer to move to the next token in the text.
     * @throws Exception a LexicalAnalyzer exception //TODO add a LexicalAnalyzerException
     */
    public void fetchNextToken() throws Exception {

        try {
            token = scanToken();
        } catch (Exception exception) {
            throw new LexerException("Unrecognized character at position "
                    + (position + 1), exception);
        }
    }

    /**
     * Scan the text and extract the next token.
     * @return the next token
     * @throws Exception;
     */
    private Token scanToken() throws Exception {
        if (position == text.length()) {
            return new Token(TokenType.END_OF_FILE, "", position);
        } else {
            int maxLength = 0;
            TokenFactory factoryWithLongestMatch = null;

            // Utilize the tokenFactories to find a factory has the longest match
            for (final TokenFactory factory : tokenFactories) {
                if (factory.find(position)) {
                    if (factory.getTokenLength() > maxLength) {
                        factoryWithLongestMatch = factory;
                        maxLength = factory.getTokenLength();
                    }
                }
            }

            // if no match is found then return null, otherwise produce a token
            if (factoryWithLongestMatch == null) {
                throw new LexerException();
            } else {
                position += factoryWithLongestMatch.getTokenLength();
                return factoryWithLongestMatch.getToken();
            }
        }
    }

    /**
     * Get the current token.
     * @return the current token
     */
    public Token getCurrentToken() {
        return token;
    }


}
