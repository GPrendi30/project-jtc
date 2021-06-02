package com.computation.test.ast;

import com.computation.ast.Type;
import com.computation.ast.intnodes.IntLiteral;
import com.computation.ast.range.NumberRange;
import com.computation.ast.range.Range;
import com.computation.ast.range.RangeException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class NumberRangeTest {

    @Test
    public void testNumberRange() throws RangeException {
        NumberRange nr = new NumberRange(new IntLiteral(1), new IntLiteral(2));

        // TODO finish this


        // assertEquals(3, nr.getValues());
    }
}
