package com.computation.test.lexer;

import com.computation.lexer.OperatorTokenFactory;
import com.computation.lexer.TokenType;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * The tests for the OperatorToken Classes.
 *
 * @author Di Pietro Enrico, Prendi Gerald.
 *
 */
public class OperatorTokenFactoryTest {

    @Test
    public void testPlusFound() {
        OperatorTokenFactory f = new OperatorTokenFactory("+", TokenType.PLUS);
        f.setText("ab+12");
        boolean found = f.find(2);
        assertTrue(found);
        assertEquals(2, f.getTokenStartPosition());
        assertEquals("+", f.getTokenText());
        assertEquals(1, f.getTokenLength());
        assertEquals(2, f.getToken().getStartPosition());
        assertEquals(TokenType.PLUS, f.getToken().getType());
        assertEquals("+", f.getToken().getText());
    }

    @Test
    public void testPlusNotFound() {
        OperatorTokenFactory f = new OperatorTokenFactory("+", TokenType.PLUS);
        f.setText("ab-12");
        boolean found = f.find(2);
        assertFalse(found);
    }

}
