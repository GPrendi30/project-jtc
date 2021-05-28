package com.computation.ast;

/**
 * An enum that types supported by our little expression language.
 */
public enum Type {
    INT(0),
    INVALID(1),
    DOUBLE(2);

    private int state;

    Type(int i) {
        this.state = i;
    }

    /**
     * Check if equals.
     * @param t Type.
     * @return boolean.
     */
    public boolean equals(final Type t) {
        return this.state == t.state;
    }
}
