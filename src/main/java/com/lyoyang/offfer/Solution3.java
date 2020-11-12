package com.lyoyang.offfer;



import java.util.ArrayList;
import java.util.Optional;
import java.util.Stack;

/**
 * 从尾到头打印链表
 */
public class Solution3 {

    public static void main(String[] args) {
        ListNode headNode = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(3);
        ListNode n4 = new ListNode(4);
        ListNode n5 = new ListNode(5);
        headNode.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        Optional.of(printListFromTailToHead(headNode)).ifPresent(System.out::println);
        Optional.of(printListFromTailToHead2(headNode)).ifPresent(System.out::println);
    }


    /**
     * 使用栈
     * @param headNode
     * @return
     */
    public static ArrayList<Integer> printListFromTailToHead(ListNode headNode) {
        ArrayList<Integer> list = new ArrayList<>();
        Stack<ListNode> stack = new Stack<>();
        while (headNode != null) {
            stack.push(headNode);
            headNode = headNode.next;
        }
        while (!stack.isEmpty()) {
            list.add(stack.pop().val);
        }
        return list;
    }


    /**
     * 递归
     * @param headNode
     * @return
     */
    public static ArrayList<Integer> printListFromTailToHead2(ListNode headNode) {
        ArrayList<Integer> list = new ArrayList<>();
        if (headNode != null) {
            if (headNode.next != null) {
                list = printListFromTailToHead2(headNode.next);
            }
            list.add(headNode.val);
        }
        return list;
    }





    static class ListNode {
        int val;
        ListNode next;
        public ListNode(int val) {
            this.val = val;
        }
    }

}
