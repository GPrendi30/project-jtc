package com.spreadsheetmodel.commands;

import java.util.ArrayList;

public class CopyPasteStack {

    private final ArrayList<String> stack;
    private static CopyPasteStack instance = new CopyPasteStack();

    private CopyPasteStack() {
        stack = new ArrayList<>();
    }

    /**
     * Pushed a string into the stack.
     * @param content a String content
     */
    public void push(final String content) {
        stack.add(content);
    }

    /**
     * Pop a value from top of the stack.
     * @return the top value.
     */
    public String pop() {
        if (stack.size() > 1) {
            final String content = stack.get(stack.size() - 1);
            stack.remove(stack.size() - 1);
            return content;
        } else {
            return null;
        }
    }

    /**
     * Peeks the value of the top of the stack, without removing it.
     * @return the value on top of the stack
     */
    public String peek() {
        if (stack.size() > 1) {
            return stack.get(stack.size() - 1);
        } else {
            return null;
        }
    }

    /**
     * Get the instance of the CopyPasteStack.
     * @return the instance of the stack.
     */
    public static CopyPasteStack getInstance() {
        return instance;
    }


}
