package com.computation.test.lexer;

import com.computation.lexer.LexicalAnalyzer;
import com.computation.lexer.OperatorTokenFactory;
import com.computation.lexer.Token;
import com.computation.lexer.TokenFactory;
import com.computation.lexer.TokenType;
import org.junit.Test;

import static org.junit.Assert.*;


public class LexicalAnalyzerTest {
    
    @Test
    public void testInitial() {
        final LexicalAnalyzer l = new LexicalAnalyzer("");
        assertNull(l.getCurrentToken());
    }
    
    @Test
    public void testEof() throws Exception {
        final LexicalAnalyzer l = new LexicalAnalyzer("");
        l.fetchNextToken();
        Token t = l.getCurrentToken();
        assertEquals(TokenType.END_OF_FILE, t.getType());
        assertEquals(0, t.getStartPosition());
    }
    
    @Test
    public void testEofNextEof() throws Exception {
        final LexicalAnalyzer l = new LexicalAnalyzer("");
        l.fetchNextToken();
        l.fetchNextToken();
        Token t = l.getCurrentToken();
        assertEquals(TokenType.END_OF_FILE, t.getType());
        assertEquals(0, t.getStartPosition());
    }
    
    @Test
    public void testOne() throws Exception {
        final LexicalAnalyzer l = new LexicalAnalyzer("+");
        l.fetchNextToken();
        Token t0 = l.getCurrentToken();
        assertEquals(TokenType.PLUS, t0.getType());
        assertEquals(0, t0.getStartPosition());
        l.fetchNextToken();
        Token t1 = l.getCurrentToken();
        assertEquals(TokenType.END_OF_FILE, t1.getType());
        assertEquals(1, t1.getStartPosition());
    }
    
    @Test
    public void testTwo() throws Exception {
        final LexicalAnalyzer l = new LexicalAnalyzer("++");
        l.fetchNextToken();
        Token t = l.getCurrentToken();
        assertEquals(TokenType.PLUS, t.getType());
        assertEquals(0, t.getStartPosition());
        l.fetchNextToken();
        t = l.getCurrentToken();
        assertEquals(TokenType.PLUS, t.getType());
        assertEquals(1, t.getStartPosition());
        l.fetchNextToken();
        t = l.getCurrentToken();
        assertEquals(TokenType.END_OF_FILE, t.getType());
        assertEquals(2, t.getStartPosition());
    }
    
    @Test
    public void testThree() throws Exception {
        final LexicalAnalyzer l = new LexicalAnalyzer("(123)");
        l.fetchNextToken();
        Token t = l.getCurrentToken();
        assertEquals(TokenType.OPEN_PAREN, t.getType());
        assertEquals(0, t.getStartPosition());
        l.fetchNextToken();
        t = l.getCurrentToken();
        assertEquals(TokenType.INTLITERAL, t.getType());
        assertEquals(1, t.getStartPosition());
        l.fetchNextToken();
        t = l.getCurrentToken();
        assertEquals(TokenType.CLOSED_PAREN, t.getType());
        assertEquals(4, t.getStartPosition());
        l.fetchNextToken();
        t = l.getCurrentToken();
        assertEquals(TokenType.END_OF_FILE, t.getType());
        assertEquals(5, t.getStartPosition());
    }
    
    @Test
    public void testIllegalToken() {
        try {
            final LexicalAnalyzer l = new LexicalAnalyzer("^");
            l.fetchNextToken();
            Token t = l.getCurrentToken();
            assertNull(t);
        } catch (Exception exception) {
            assertTrue(exception instanceof Exception);
        }

    }
    
    @Test
    public void testLongestFoundToken() throws Exception {
        final LexicalAnalyzer l = new LexicalAnalyzer("++", new TokenFactory[] {
            new OperatorTokenFactory("++", TokenType.STAR),
            new OperatorTokenFactory("+", TokenType.PLUS)
        });
        l.fetchNextToken();
        Token t = l.getCurrentToken();
        assertEquals(TokenType.STAR, t.getType());
        assertEquals("++", t.getText());
    }
    
}
