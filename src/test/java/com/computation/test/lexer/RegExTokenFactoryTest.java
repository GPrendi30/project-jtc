package com.computation.test.lexer;

import com.computation.lexer.RegExTokenFactory;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * The tests for the RegExTokenFactory Classes.
 *
 * @author Di Pietro Enrico, Prendi Gerald.
 *
 */
public class RegExTokenFactoryTest {

    @Test
    public void testMatchFixed() {
        RegExTokenFactory f = new RegExTokenFactory("hi");
        f.setText("XXhiXX");
        boolean found = f.find(2);
        assertTrue(found);
        assertEquals(2, f.getTokenStartPosition());
        assertEquals(2, f.getTokenLength());
        assertEquals("hi", f.getTokenText());
    }
    
    @Test
    public void testMatchFlexible() {
        RegExTokenFactory f = new RegExTokenFactory("ha*");
        f.setText("XhaaaX");
        boolean found = f.find(1);
        assertTrue(found);
        assertEquals(1, f.getTokenStartPosition());
        assertEquals(4, f.getTokenLength());
        assertEquals("haaa", f.getTokenText());
    }
    
    @Test
    public void testMatchThereAndNotEarlier() {
        RegExTokenFactory f = new RegExTokenFactory("hi");
        f.setText("XhiXhiX");
        boolean found = f.find(4);
        assertTrue(found);
        assertEquals(4, f.getTokenStartPosition());
    }
    
    @Test
    public void testMatchThereAndNotLater() {
        RegExTokenFactory f = new RegExTokenFactory("hi");
        f.setText("XhiXhiX");
        boolean found = f.find(1);
        assertTrue(found);
        assertEquals(1, f.getTokenStartPosition());
    }
    
    @Test
    public void testNoMatchNowhere() {
        RegExTokenFactory f = new RegExTokenFactory("hi");
        f.setText("abc");
        assertFalse(f.find(0));
        assertFalse(f.find(1));
        assertFalse(f.find(2));
    }
    
    @Test(expected = IndexOutOfBoundsException.class)
    public void testStartOutOfBounds() {
        RegExTokenFactory f = new RegExTokenFactory("hi");
        f.setText("abc");
        f.find(4); // this call must throw an IOOB exception
    }
    
}
