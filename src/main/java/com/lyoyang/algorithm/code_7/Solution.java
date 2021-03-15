package com.lyoyang.algorithm.code_7;

import java.util.*;

public class Solution {

    public boolean isValid(String s) {
        HashMap<String, String> map = new HashMap<>();
        map.put(")", "(");
        map.put("}", "{");
        map.put("]", "[");
        LinkedList<String> list = new LinkedList<>();
        if (s.isEmpty()) {
            return true;
        }
        for (String str : s.split("")) {
            if (map.containsValue(str)) {
                list.push(str);
            } else if (list.isEmpty() || !map.get(str).equals(list.pop())) {
                return false;
            }
        }
        return list.isEmpty();
    }

    public boolean isValid2(String s) {
        if (s.isEmpty()) {
            return true;
        }
        Stack<Character> stack = new Stack<>();
        for (char ch : s.toCharArray()) {
            if (ch == '(') {
                stack.push(')');
            } else if (ch == '[') {
                stack.push(']');
            } else if (ch == '{') {
                stack.push('}');
            } else if (stack.isEmpty() || ch != stack.pop()) {
                return false;
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.isValid("()[]{}"));
        System.out.println(solution.isValid2("（]"));
    }



}
