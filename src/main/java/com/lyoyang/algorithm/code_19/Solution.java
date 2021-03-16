package com.lyoyang.algorithm.code_19;

import com.lyoyang.offfer.ListNode;

/**
 * 删除链表倒数第n个节点，并且返回链表的头结点
 */
public class Solution {

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0, head);
        int length = getLength(head);
        ListNode curr = dummy;
        for (int i = 1; i < length; i++) {
            curr = curr.next;
        }
        curr.next = curr.next.next;
        ListNode ans = dummy.next;
        return ans;
    }

    public int getLength(ListNode head) {
        int length = 0;
        while (head != null) {
            head = head.next;
            ++length;
        }
        return length;
    }

}
