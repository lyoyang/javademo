package com.lyoyang.algorithm.offer_09;

import java.util.Stack;

public class CQueue {

    private Stack<Integer> s1;

    private Stack<Integer> s2;

    public CQueue() {
        s1 = new Stack<>();
        s2 = new Stack<>();
    }

    public void appendTail(int value) {
        s1.push(value);
    }

    public int deleteHead() {
        if (!s2.isEmpty()) {
            return s2.pop();
        }
        if (s1.isEmpty()) {
           return -1;
        }
        while (!s1.isEmpty()) {
           s2.push(s1.pop());
        }
        return s2.pop();
    }

    public static void main(String[] args) {
        CQueue cQueue = new CQueue();
        System.out.println(cQueue.deleteHead());
        cQueue.appendTail(1);
        cQueue.appendTail(2);
        cQueue.appendTail(3);
        System.out.println(cQueue.deleteHead());
        System.out.println(cQueue.deleteHead());
        System.out.println(cQueue.deleteHead());
        System.out.println(cQueue.deleteHead());
        cQueue.appendTail(7);
        System.out.println(cQueue.deleteHead());
    }

}
