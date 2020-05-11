package com.lyoyang.datastructure;

/**
 * 顺序栈
 */
public class ArrayStack {
    private String[] items;
    //元素个数
    private int count;
    //栈的大小
    private int n;

    public ArrayStack(int n) {
        this.items = new String[n];
        this.n = n;
        this.count = 0;
    }

    /**
     * 出栈
     * @return
     */
    public String pop() {
        if (count == 0) {
            return null;
        }
        String data = items[count-1];
        --count;
        return data;
    }

    /**
     * 入栈
     * @param data
     * @return
     */
    public boolean push(String data) {
        if (count == n) {
            return false;
        }
        items[count] = data;
        ++count;
        return true;
    }


    public static void main(String[] args) {
        ArrayStack arrayStack = new ArrayStack(5);


    }
}
