package com.computation.test.lexer;

import com.computation.lexer.Token;
import com.computation.lexer.TokenType;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * The tests for the Token Classes.
 *
 * @author Di Pietro Enrico, Prendi Gerald.
 *
 */
public class TokenTest {
    
    @Test
    public void testLength1() {
        Token t = new Token(TokenType.PLUS, "+", 0);
        assertEquals(TokenType.PLUS, t.getType());
        assertEquals("+", t.getText());
        assertEquals(0, t.getStartPosition());
        assertEquals(1, t.getEndPosition());
    }
    
    @Test
    public void testLength2() {
        Token t = new Token(TokenType.IDENTIFIER, "id", 3);
        assertEquals(TokenType.IDENTIFIER, t.getType());
        assertEquals("id", t.getText());
        assertEquals(3, t.getStartPosition());
        assertEquals(5, t.getEndPosition());
    }
    
    @Test
    public void testLength3() {
        Token t = new Token(TokenType.INTLITERAL, "456", 60);
        assertEquals(TokenType.INTLITERAL, t.getType());
        assertEquals("456", t.getText());
        assertEquals(60, t.getStartPosition());
        assertEquals(63, t.getEndPosition());
    }
    
}
