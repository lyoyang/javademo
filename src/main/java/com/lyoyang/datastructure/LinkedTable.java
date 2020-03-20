package com.lyoyang.datastructure;

import lombok.Data;

/**
 * 单链表
 */
public class LinkedTable<T> {

    private Node<T> head;

    public LinkedTable(T data) {
        this.head = new Node(data);
    }

    public Node findByValue(T data) {
        Node currentNode = this.head;
        while (currentNode != null && !currentNode.getData().equals(data)) {
            currentNode = currentNode.next;
        }
        return currentNode == null ? null : currentNode;
    }


    public Node findByIndex(int index) {
        int pos = 0;
        Node currentNode = this.head;
        while (currentNode != null && pos != index) {
            currentNode = currentNode.next;
            pos++;
        }
        return currentNode == null ? null : currentNode;
    }

    /**
     * 在指定元素后边插入
     * @param data
     * @param element
     */
    public void insert(T data, T element)  {
        Node currentNode = findByValue(element);
        assert currentNode != null : "not found emement";
        Node newNode = new Node(data);
        newNode.next = currentNode.next;
        currentNode.next = newNode;
    }


    /**
     * 根据数据删除节点
     * @param data
     */
    public void delete(T data) {
        Node currentNode = this.head;
        Node preNode = null;
        while (currentNode != null && !currentNode.getData().equals(data)) {
            preNode = currentNode;
            currentNode = currentNode.next;
        }
        if (currentNode == null) return;
        preNode.next = currentNode.next;
    }


    /**
     * 遍历节点
     */
    public void print() {
        Node currentNode = head;
        while (currentNode != null) {
            System.out.print(currentNode.data + " ");
            currentNode = currentNode.next;
        }
        System.out.println();
    }

    public void insertFirst(T data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
            return;
        }
        newNode.next = head;
        head = newNode;
    }

    public void insertLast(T data) {
        Node currentNode = head;
        while (currentNode.next != null) {
            currentNode = currentNode.next;
        }
        currentNode.next = new Node(data);
    }

    public T removeFirst() {
        if (head == null) {
            return null;
        }
        T data = head.data;
        head = head.next;
        return data;
    }

    public T removeLast() {
        Node<T> currentNode = head;
        Node<T> preNode = null;
        if (currentNode == null) {
            return null;
        }
        while (currentNode.next != null) {
            preNode = currentNode;
            currentNode = currentNode.next;
        }
        if (preNode == null)  {
            T data = currentNode.getData();
            head = null;
            return data;
        }
        preNode.next = null;
        return currentNode.getData();
    }


    public static void main(String[] args) {
        LinkedTable linkedTable = new LinkedTable("1");
        linkedTable.insertLast(2);
        linkedTable.insertLast(3);
        linkedTable.insertLast(4);
        linkedTable.print();
        linkedTable.delete(3);
        linkedTable.print();
        linkedTable.removeFirst();
        linkedTable.print();
        linkedTable.removeLast();
        linkedTable.print();
        System.out.println(linkedTable.removeLast());
        System.out.println(linkedTable.removeLast());
        linkedTable.print();
        System.out.println(linkedTable.removeFirst());
        System.out.println(linkedTable.removeFirst());
    }





    @Data
    class Node<T> {

        public Node(T data) {
            this.data = data;
            this.next = null;
        }

        private T data;

        private Node<T> next;

    }

}
