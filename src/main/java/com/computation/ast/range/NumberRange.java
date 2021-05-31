package com.computation.ast.range;

import com.computation.ast.Node;
import com.computation.ast.Type;
import com.computation.ast.intnodes.IntLiteral;

public class NumberRange extends Range {


    private final ArrayNode value;


    /**
     * A number range representation.
     * @param start the start Node.
     * @param end the end Node.
     * @throws Exception throws Exception if the Range cannot be parsed.
     */
    public NumberRange(final Node start, final Node end) throws Exception {
        super(start, end);
        value = new ArrayNode(Type.INT);

        try {
            parseRange();
        } catch (Exception exception) {
            throw new Exception("Range is unparasable", exception);
        }
    }

    /**
     * Gets the ArrayNode values stored in the Range.
     * @return the Array of values.
     */
    public Node getValue() {
        return value;
    }

    private void parseRange() throws Exception {
        final int startIndex = Integer.parseInt(start.toString());
        final int endIndex = Integer.parseInt(end.toString());
        for (int i = startIndex; i <= endIndex; i++) {
            final Node tempNode = new IntLiteral(i);
            value.append(tempNode);
        }
    }
}
