package com.lyoyang.offfer;

/**
 * 给定两个升序链表，打印两个升序链表的公共部分
 * 示例：
 * 4
 * 1 2 3 4
 * 5
 * 1 2 3 5 6
 * 输出： 1 2 3
 */

public class PrintCommonPart {


    public static void printCommonPart(ListNode head1, ListNode head2) {
        StringBuilder stringBuilder = new StringBuilder();
        ListNode node1 = head1;
        ListNode node2 = head2;
        while (node1 != null && node2 != null) {
            if (node1.val == node2.val) {
                stringBuilder.append(node1.val).append(" ");
            } else if (node1.val < node2.val) {
                node1 = node1.next;
            } else {
                node2 = node2.next;
            }
        }
        System.out.println(stringBuilder.toString());
    }

}
