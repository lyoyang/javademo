package com.lyoyang.algorithm;

import java.util.Stack;

/**
 * @author: Brian
 * @Date: 2020/7/1 10:45
 * @Description:
 * 构造一个栈，pop()，push()，min()，max()复杂度都是o(1)
 */
public class MinMaxStack {

    private Stack<Integer> stack = new Stack<>();

    private Stack<Integer> minStack = new Stack<>();

    private Stack<Integer> maxStack = new Stack<>();

    public void push(int value) {
        stack.push(value);
        if (minStack.empty() || minStack.peek() > value) {
            minStack.push(value);
        }
        if (maxStack.empty() || maxStack.peek() < value) {
            maxStack.push(value);
        }
    }


    public int pop() {
        if (stack.empty()) {
            throw new RuntimeException("stack is empty");
        }
        int popValue = stack.pop();
        if (minStack.peek() == popValue) {
            minStack.pop();
        }
        if (maxStack.peek() == popValue) {
            maxStack.pop();
        }
        return popValue;
    }


    public int getMin() {
        if (minStack.empty()) {
            throw new RuntimeException("stack is empty");
        }
        return minStack.peek();
    }

    public int getMax() {
        if (maxStack.empty()) {
            throw new RuntimeException("stack is empty");
        }
        return maxStack.peek();
    }


    public static void main(String[] args) {
        MinMaxStack minMaxStack = new MinMaxStack();
        minMaxStack.push(3);
        minMaxStack.push(2);
        minMaxStack.push(12);
        minMaxStack.push(4);
        minMaxStack.push(3);
        minMaxStack.push(7);
        System.out.println(String.format("min:%s, max:%s", minMaxStack.getMin(), minMaxStack.getMax()));
        minMaxStack.pop();
        System.out.println(String.format("min:%s, max:%s", minMaxStack.getMin(), minMaxStack.getMax()));
        minMaxStack.pop();
        System.out.println(String.format("min:%s, max:%s", minMaxStack.getMin(), minMaxStack.getMax()));
        minMaxStack.push(1);
        System.out.println(String.format("min:%s, max:%s", minMaxStack.getMin(), minMaxStack.getMax()));
        minMaxStack.pop();
        System.out.println(String.format("min:%s, max:%s", minMaxStack.getMin(), minMaxStack.getMax()));
    }








}
