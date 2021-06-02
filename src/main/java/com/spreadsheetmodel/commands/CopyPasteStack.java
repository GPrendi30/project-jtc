package com.spreadsheetmodel.commands;

import java.util.ArrayList;

public class CopyPasteStack {

    private final ArrayList<String> stack;
    private static CopyPasteStack instance = new CopyPasteStack();

    private CopyPasteStack() {
        stack = new ArrayList<>();
    }

    public void push(final String content) {
        stack.add(content);
    }

    public String pop() {
        if (stack.size() > 1) {
            final String content = stack.get(stack.size() - 1);
            stack.remove(stack.size() - 1);
            return content;
        } else {
            return null;
        }
    }

    public String peek() {
        if (stack.size() > 1) {
            return stack.get(stack.size() - 1);
        } else {
            return null;
        }
    }

    public static CopyPasteStack getInstance() {
        return instance;
    }


}
