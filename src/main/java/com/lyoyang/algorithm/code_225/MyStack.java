package com.lyoyang.algorithm.code_225;

import java.util.LinkedList;

public class MyStack {

    private LinkedList<Integer> q1 = new LinkedList<>();
    private LinkedList<Integer> q2 = new LinkedList<>();


    public MyStack() {

    }

    /** Push element x onto stack. */
    public void push(int x) {
        q1.offer(x);
        while (!q2.isEmpty()) {
            q1.offer(q2.poll());
        }
        LinkedList tmp = q1;
        q1 = q2;
        q2 = tmp;
    }


    /**
     * ["MyStack","push","push","top","pop","empty"]
     * [[],[1],[2],[],[],[]]
     */
    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
       return q2.poll();
    }

    /** Get the top element. */
    public int top() {
       return q2.peek();
    }

    /** Returns whether the stack is empty. */
    public boolean empty() {
        return q2.isEmpty();
    }


    public static void main(String[] args) {
        MyStack myStack = new MyStack();
        myStack.push(1);
        myStack.push(2);
        System.out.println(myStack.top());
        System.out.println(myStack.pop());
        System.out.println(myStack.empty());
    }



}
