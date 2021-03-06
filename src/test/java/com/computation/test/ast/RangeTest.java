package com.computation.test.ast;

import com.computation.ast.Type;
import com.computation.ast.intnodes.IntLiteral;
import com.computation.ast.range.Range;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * The tests for the Range Classes.
 *
 * @author Di Pietro Enrico, Prendi Gerald.
 *
 */
public class RangeTest {

    @Test
    public void testRange() {
        Range r = new Range(new IntLiteral(1), new IntLiteral(2));
        assertEquals("1", r.getStart().toString());
        assertEquals("2", r.getEnd().toString());
    }

    @Test
    public void testGetType() {
        Range r = new Range(new IntLiteral(1), new IntLiteral(2));
        assertEquals(Type.INVALID, r.getType());
    }

    @Test
    public void testIsConstant() {
        Range r = new Range(new IntLiteral(1), new IntLiteral(2));
        assertFalse(r.isConstant());
    }

    @Test
    public void testToString() {
        Range r = new Range(new IntLiteral(1), new IntLiteral(2));
        assertEquals("1:2", r.toString());
    }
}
