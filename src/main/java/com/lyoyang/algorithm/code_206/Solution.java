package com.lyoyang.algorithm.code_206;

/**
 * 反转单链表
 */
public class Solution {


    public ListNode reverseList(ListNode head) {
        ListNode currentNode = head;
        ListNode prevNode = null;
        while (currentNode != null) {
            ListNode tmpNode = currentNode.next;
            currentNode.next = prevNode;
            prevNode = currentNode;
            currentNode = tmpNode;
        }
        return prevNode;
    }

    public ListNode reverseList2(ListNode head) {
       if (head == null || head.next == null) {
           return head;
       }
       ListNode p = reverseList2(head.next);
       head.next.next = head;
       head.next = null;
       return p;
    }




    class ListNode {
        int val;
        ListNode next;
        ListNode() {};
        ListNode(int val) {
            this.val = val;
        }
        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

}
