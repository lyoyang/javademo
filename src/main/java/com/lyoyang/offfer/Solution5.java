package com.lyoyang.offfer;

import java.util.Stack;

/**
 * 用两个栈来实现一个队列， 完成队列的 Push 和 Pop 操作。 队列中的
 * 元素为 int 类型
 *
 * 一个栈压入元素， 而另一个栈作为缓冲， 将栈 1 的元素出栈后压入栈 2
 * 中。 也可以将栈 1 中的最后一个元素直接出栈， 而不用压入栈 2 中再出栈
 *
 *
 */
public class Solution5 {

    private Stack<Integer> stack1;
    private Stack<Integer> stack2;


    public void push(int val) {
        stack1.push(val);
    }

    public int pop() throws Exception {
        if (stack1.isEmpty() && stack2.isEmpty()) {
            throw new Exception("stack is empty");
        }
        if (stack2.isEmpty()) {
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
        }
        return stack2.pop();
    }

}
