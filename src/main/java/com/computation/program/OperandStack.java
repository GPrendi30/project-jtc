package com.computation.program;

/**
 * The OperandStack is a stack holding
 * the temporary values during execution.
 * (If you took Computer Architecture at USI,
 * you should remember this).
 */
public class OperandStack {
    
    private Double[] stack;
    private int sp;
    
    /**
     * Create an empty OperandStack.
     * with space for at most 10 elements!
     */
    public OperandStack() {
        stack = new Double[10];
        sp = -1;
    }
    
    
    /**
     * Push the given value on the stack.
     * @param value The value to ipush
     */
    public void ipush(final int value) {
        checkToGrow();
        stack[++sp] = (double) value;
    }
    
    /**
     * Push the given value on the stack.
     * @param value The value to ipush
     */

    public void dpush(final double value) {
        checkToGrow();
        stack[++sp] = value; 
    }
    
    /**
     *  Pop the top-most value off the stack.
     * @return the top-most value
     */
    public int ipop() {
        return stack[sp--].intValue();
    }

    /**
     * Pop the top-most value off the stack.
     * @return the top-most value
     */
    public double dpop() {
        return stack[sp--];
    }
    
    private void checkToGrow() {
        if (sp >= stack.length - 1) {
            growStack();
        }
    }
    
    private void growStack() {
        final int newSize = stack.length * 2;
        Double[] newStack = new Double[newSize];
        for (int i = 0; i < stack.length; i++) {
            newStack[i] = stack[i];
        }
        this.stack = newStack;
    }
}
