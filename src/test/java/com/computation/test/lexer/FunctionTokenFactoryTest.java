package com.computation.test.lexer;

import com.computation.lexer.FunctionTokenFactory;
import com.computation.lexer.Token;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FunctionTokenFactoryTest {
    
    @Test
    public void testFunctionTokenFactory() {
        FunctionTokenFactory ftf = new FunctionTokenFactory("~12aB0-");

        Token token = ftf.getToken();

        assertEquals("FUNCTION", token.getType().toString());
        assertEquals("~12aB0-", token.getText());
        assertEquals(0, token.getStartPosition());

    }
}
