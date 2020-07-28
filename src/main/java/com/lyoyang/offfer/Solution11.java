package com.lyoyang.offfer;

/**
 * 反转链表
 */
public class Solution11 {

    public static void main(String[] args) {
        System.out.println(4 & (-4));
    }

    /**
     * 双指针 反向输出 时间复杂度O（n） 时间复杂度O（1）
     * @param head
     * @return
     */
    public static ListNode reverseList(ListNode head) {
        ListNode pre = null;
        ListNode curr = head;
        while (curr != null) {
           ListNode tmp = curr.next;
           curr.next = pre;
           pre = curr;
           curr = tmp;
        }
        return pre;
    }






}

