package com.lyoyang.algorithm.leetcode_2;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: yangbing
 * @Date: 2019/11/12 11:30
 * @Description:
 * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
 *
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 *
 * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 *
 * 示例：
 *
 * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 0 -> 8
 * 原因：342 + 465 = 807
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/add-two-numbers
 */
public class AddTwoNumber {

    /**
     * m,n代表了l1,l2的链表长度
     * 时间复杂度：O(max(m,n))
     * 空间复杂度：O(max(m,n))
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        //预先指针
        ListNode root = new ListNode(0);
        ListNode cusor = root;
        int carray = 0;
        while (l1 != null || l2 != null || carray != 0) {
            int v1 = l1 != null ? l1.val : 0;
            int v2 = l2 != null ? l2.val : 0;
            int sumVal = v1 + v2 + carray;
            carray = sumVal / 10;
            ListNode sumNode = new ListNode(sumVal % 10);
            cusor.next = sumNode;
            cusor = sumNode;
            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;
        }
        return root.next;
    }


    /**时间复杂度：O(max(m,n))
     * 空间复杂度：O(1)
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        ListNode head = l1;
        int carray = 0;
        while (l1 != null || l2 != null) {
            l1.val += carray;
            carray = (l1.val + l2.val) / 10;
            l1.val = (l1.val + l2.val) % 10;
            if (l1.next != null || l2.next != null) {
                if (l1.next == null) l1.next = new ListNode(0);
                if (l2.next == null) l2.next = new ListNode(0);
            } else if (carray != 0) {
                l1.next = new ListNode(carray);
                break;
            }
            l1 = l1.next;
            l2 = l2.next;
        }
        return head;
    }


    public static void main(String[] args) {
        ListNode l1 = new ListNode(3);
        ListNode l2 = new ListNode(4);
        ListNode l3 = new ListNode(5);
        l1.next = l2;
        l2.next = l3;
        ListNode n1 = new ListNode(6);
        ListNode n2 = new ListNode(7);
        ListNode n3 = new ListNode(8);
        n1.next = n2;
        n2.next = n3;
        AddTwoNumber addTwoNumber = new AddTwoNumber();
        ListNode listNode = addTwoNumber.addTwoNumbers2(l1, n1);
        addTwoNumber.printMode(listNode);
    }


    public void printMode(ListNode listNode) {
        List<Integer> list = new ArrayList<>();
        while (listNode != null) {
            list.add(listNode.val);
            listNode = listNode.next;
        }
        System.out.println(list.toString());
    }



}

class ListNode {
    int val;
    ListNode next;
    public ListNode(int val) {
        this.val = val;
    }
}

