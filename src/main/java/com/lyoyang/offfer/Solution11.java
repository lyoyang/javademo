package com.lyoyang.offfer;

/**
 * 反转链表
 */
public class Solution11 {

    /**
     * 双指针 反向输出 时间复杂度O（n） 时间复杂度O（1）
     * @param head
     * @return
     */
    public static Linkedode reverseList(Linkedode head) {
        Linkedode pre = null;
        Linkedode curr = head;
        while (curr != null) {
           Linkedode tmp = curr.next;
           curr.next = pre;
           pre = curr;
           curr = tmp;
        }
        return pre;
    }






}

