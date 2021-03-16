package com.lyoyang.algorithm.code_141;

import com.lyoyang.offfer.ListNode;

public class Solution {

    public boolean hasCycle(ListNode head) {
       ListNode fastNode = head;
       ListNode slowNode = head;
       while (fastNode != null && slowNode != null && fastNode.next != null) {
           slowNode = slowNode.next;
           fastNode = fastNode.next.next;
           if (fastNode == slowNode) {
               return true;
           }
       }
       return false;
    }


}
