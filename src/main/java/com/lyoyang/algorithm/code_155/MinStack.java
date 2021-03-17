package com.lyoyang.algorithm.code_155;

import java.util.Stack;

public class MinStack {

    private Stack<Integer> minStack;

    private Stack<Integer> stack;


    public MinStack() {
        this.minStack = new Stack<>();
        this.stack = new Stack<>();
    }

    public void push(int x) {
        stack.push(x);
        if (minStack.isEmpty() || minStack.peek() >= x) {
            minStack.push(x);
        }
    }

    public void pop() {
        Integer pop = stack.pop();
        if (minStack.peek().equals(pop)) {
            minStack.pop();
        }

    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
       return minStack.peek();
    }

    public static void main(String[] args) {
        MinStack minStack = new MinStack();
        minStack.push(0);
        minStack.push(1);
        minStack.push(0);
        System.out.println(minStack.getMin());
    }


}
