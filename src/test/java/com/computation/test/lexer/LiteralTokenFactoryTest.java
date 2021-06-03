package com.computation.test.lexer;

import com.computation.lexer.LiteralTokenFactory;
import com.computation.lexer.TokenType;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * The tests for the LiteralToken Classes.
 *
 * @author Di Pietro Enrico, Prendi Gerald.
 *
 */
public class LiteralTokenFactoryTest {
    
    @Test
    public void testFoundLength1() {
        LiteralTokenFactory f = new LiteralTokenFactory();
        f.setText("a=9x");
        boolean found = f.find(2);
        assertTrue(found);
        assertEquals(1, f.getTokenLength());
        assertEquals(2, f.getTokenStartPosition());
        assertEquals("9", f.getTokenText());
        assertEquals(TokenType.INTLITERAL, f.getToken().getType());
    }
    
    @Test
    public void testFoundLength3() {
        LiteralTokenFactory f = new LiteralTokenFactory();
        f.setText("abc=123x");
        boolean found = f.find(4);
        assertTrue(found);
        assertEquals(3, f.getTokenLength());
        assertEquals(4, f.getTokenStartPosition());
        assertEquals("123", f.getTokenText());
        assertEquals(TokenType.INTLITERAL, f.getToken().getType());
    }
    
    @Test
    public void testNoMatchNotFound() {
        LiteralTokenFactory f = new LiteralTokenFactory();
        f.setText("abc=xyz");
        boolean found = f.find(4);
        assertFalse(found);
    }
}
