package com.computation.test.lexer;

import com.computation.lexer.StringTokenFactory;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * The tests for the StringTokenFactory Classes.
 *
 * @author Di Pietro Enrico, Prendi Gerald.
 *
 */
public class StringTokenFactoryTest {

    @Test
    public void testMatchOne() {
        StringTokenFactory f = new StringTokenFactory("a");
        f.setText("XXaXX");
        boolean found = f.find(2);
        assertTrue(found);
        assertEquals(2, f.getTokenStartPosition());
        assertEquals(1, f.getTokenLength());
        assertEquals("a", f.getTokenText());
    }
    
    @Test
    public void testMatchTwo() {
        StringTokenFactory f = new StringTokenFactory("ha");
        f.setText("XhaX");
        boolean found = f.find(1);
        assertTrue(found);
        assertEquals(1, f.getTokenStartPosition());
        assertEquals(2, f.getTokenLength());
        assertEquals("ha", f.getTokenText());
    }
    
    @Test
    public void testMatchThereAndNotEarlier() {
        StringTokenFactory f = new StringTokenFactory("hi");
        f.setText("XhiXhiX");
        boolean found = f.find(4);
        assertTrue(found);
        assertEquals(4, f.getTokenStartPosition());
    }
    
    @Test
    public void testMatchThereAndNotLater() {
        StringTokenFactory f = new StringTokenFactory("hi");
        f.setText("XhiXhiX");
        boolean found = f.find(1);
        assertTrue(found);
        assertEquals(1, f.getTokenStartPosition());
    }
    
    @Test
    public void testNoMatchNowhere() {
        StringTokenFactory f = new StringTokenFactory("hi");
        f.setText("abc");
        assertFalse(f.find(0));
        assertFalse(f.find(1));
        assertFalse(f.find(2));
    }
    
    @Test
    public void testStartOutOfBounds() {
        StringTokenFactory f = new StringTokenFactory("hi");
        f.setText("abc");
        assertFalse(f.find(4)); // this call does not throw an IOOB exception
    }
    
}
