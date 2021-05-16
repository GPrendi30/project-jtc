package com.computation.program;

/**
 * The OperandStack is a stack holding
 * the temporary values during execution.
 * (If you took Computer Architecture at USI,
 * you should remember this).
 */
public class OperandStack {
    
    private int[] stack;
    private int sp;
    
    /**
     * Create an empty OperandStack.
     * with space for at most 10 elements!
     */
    public OperandStack() {
        stack = new int[10];
        sp = -1;
    }
    
    /**
     * Push the given value on the stack.
     * @param value The value to push
     */
    public void push(final int value) {
        if (sp >= stack.length - 1) {
            growStack();
        }
        stack[++sp] = value;
    }
    
    /**
     * Pop the top-most value off the stack.
     * @return the top-most value
     */
    public int pop() {
        return stack[sp--];
    }

    private void growStack() {
        int newSize = stack.length * 2;
        int[] newStack = new int[newSize];
        for (int i = 0; i < stack.length; i++) {
            newStack[i] = stack[i];
        }
        this.stack = newStack;
    }
}
