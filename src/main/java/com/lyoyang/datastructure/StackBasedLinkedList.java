package com.lyoyang.datastructure;

/**
 * 链式栈
 */
public class StackBasedLinkedList {

    private Node top;

    /**
     * 入栈
     * @param data
     */
    public void push(int data) {
        Node newNode = new Node(data);
        if (top == null) {
            top = newNode;
        } else {
            newNode.next = top;
            top = newNode;
        }

    }

    /**
     * 出栈
     * @return
     */
    public int pop() {
        if (top == null) {
            return -1;
        } else {
            int value = top.getData();
            top = top.next;
            return value;
        }
    }

    public void print() {
        Node currentNode = top;
        while (currentNode != null) {
            System.out.print(currentNode.getData() + " ");
            currentNode = currentNode.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        StackBasedLinkedList stack = new StackBasedLinkedList();
        System.out.println(stack.pop());
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);
        stack.print();
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        stack.print();
    }



    private static class Node {

        private int data;

        private Node next;

        public Node(int data) {
            this.data = data;
            this.next = null;
        }

        public Node(int data, Node next) {
            this.data = data;
            this.next = next;
        }

        public int getData() {
            return data;
        }
    }

}
