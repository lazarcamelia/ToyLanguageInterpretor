package model.adt;

import java.util.Stack;

public class ADTStack<T> implements IStack<T> {
    private Stack<T> stack;

    public ADTStack() {
        stack = new Stack<>();
    }

    @Override
    public void push(T elem) {
        stack.push(elem);
    }

    @Override
    public T pop() {
        return stack.pop();
    }

    @Override
    public boolean isEmpty() {
        return stack.isEmpty();
    }

    @Override
    public String toString() {
        return stack.toString();
    }
}
