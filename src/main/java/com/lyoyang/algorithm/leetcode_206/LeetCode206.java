package com.lyoyang.algorithm.leetcode_206;

import com.lyoyang.offfer.ListNode;

/**
 * 反转一个单链表。
 *
 * 示例:
 *
 * 输入: 1->2->3->4->5->NULL
 * 输出: 5->4->3->2->1->NULL
 */

public class LeetCode206 {


    public ListNode reverseList(ListNode head) {
        ListNode currentNode = head;
        ListNode preNode = null;
        while (currentNode != null) {
            ListNode tmpNode = currentNode.next;
            currentNode.next = preNode;
            preNode = currentNode;
            currentNode = tmpNode;
        }
        return preNode;
    }

}
