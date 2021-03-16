package com.lyoyang.algorithm.offer_25;

import com.lyoyang.offfer.ListNode;

/**
 * 合并2个有序链表
 */
public class Solution {

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode currentNode = dummy;
        while (l1 != null && l2 != null) {
            if (l1.val > l2.val) {
                currentNode.next = l2;
                currentNode = l2;
                l2 = l2.next;
            } else {
                currentNode.next = l1;
                currentNode = l1;
                l1 = l1.next;
            }
        }
        if (l1 != null) {
            currentNode.next = l1;
        }
        if (l2 != null) {
            currentNode.next = l2;
        }
        return dummy.next;
    }


    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(2);
        ListNode l3 = new ListNode(3);
        l1.next = l2;
        l2.next = l3;
        ListNode s1 = new ListNode(1);
        ListNode s2 = new ListNode(3);
        ListNode s3 = new ListNode(4);
        s1.next = s2;
        s2.next = s3;
        Solution solution = new Solution();
        ListNode listNode = solution.mergeTwoLists(l1, s1);
        while (listNode != null) {
            System.out.println(listNode.val);
            listNode = listNode.next;
        }

    }


}
