package com.computation.test.lexer;

import com.computation.lexer.IdentifierTokenFactory;
import com.computation.lexer.TokenType;
import org.junit.Test;

import static org.junit.Assert.*;


public class IdentifierTokenFactoryTest {
    
       
    @Test
    public void testFoundLength1() {
        IdentifierTokenFactory f = new IdentifierTokenFactory();
        f.setText("a=9x");
        boolean found = f.find(3);
        assertTrue(found);
        assertEquals(1, f.getTokenLength());
        assertEquals(3, f.getTokenStartPosition());
        assertEquals("x", f.getTokenText());
        assertEquals(TokenType.IDENTIFIER, f.getToken().getType());
    }
    
    @Test
    public void testFoundLength3() {
        IdentifierTokenFactory f = new IdentifierTokenFactory();
        f.setText("abc=1x1a+");
        boolean found = f.find(5);
        assertTrue(found);
        assertEquals(3, f.getTokenLength());
        assertEquals(5, f.getTokenStartPosition());
        assertEquals("x1a", f.getTokenText());
        assertEquals(TokenType.IDENTIFIER, f.getToken().getType());
    }
    
    @Test
    public void testNoMatchNotFound() {
        IdentifierTokenFactory f = new IdentifierTokenFactory();
        f.setText("123=456");
        boolean found = f.find(4);
        assertFalse(found);
    }

}
