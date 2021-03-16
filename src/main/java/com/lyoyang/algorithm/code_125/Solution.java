package com.lyoyang.algorithm.code_125;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * 回文字符串判断
 */
public class Solution {

    public boolean isPalindrome(String s) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            if (Character.toString(s.charAt(i)).matches("[0-9A-Za-z]")) {
                list.add(Character.toString(s.charAt(i)));
            }
        }
        int i = 0, j = list.size() - 1;
        while (i < j) {
            if (!list.get(i).toLowerCase().equals(list.get(j).toLowerCase())) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }


    public boolean isPalindrome2(String s) {
        int i = 0, j = s.length() - 1;
        while (i < j) {
            if (!Character.isLetterOrDigit(s.charAt(i))) {
                i++;
                continue;
            }
            if (!Character.isLetterOrDigit(s.charAt(j))) {
                j--;
                continue;
            }
            if (!(Character.toLowerCase(s.charAt(i)) == Character.toLowerCase(s.charAt(j)))) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }

}
