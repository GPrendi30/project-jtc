package com.computation.ast;

/**
 * An enum that types supported by our little expression language.
 *
 * @author Di Pietro Enrico, Prendi Gerald.
 *
 */

public enum Type {
    INT(0),
    INVALID(1),
    DOUBLE(2),
    ARRAY(3);

    private int state;

    Type(final int i) {
        this.state = i;
    }

    /**
     * Check if equals.
     * @param t Type.
     * @return boolean.
     */
    public boolean equalsType(final Type t) {
        return this.state == t.state;
    }
}
