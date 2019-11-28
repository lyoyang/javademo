package com.lyoyang.algorithm.leetcode_3;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author: yangbing
 * @Date: 2019/11/12 18:59
 * @Description:
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 *
 * 示例 1:
 *
 * 输入: "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * 示例 2:
 *
 * 输入: "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 * 示例 3:
 *
 * 输入: "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串
 * 链接：https://leetcode-cn.com/problems/longest-substring-without-repeating-characters
 */
public class LengthOfLongestSubstring {

    /**
     * 暴力查找
     * @param s
     * @return
     */
    public int solve1(String s) {
        int n = s.length();
        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i; j <= n; j++) {
                if (allUnique(s, i, j)) ans = Math.max(ans, j - i);
            }
        }
        return ans;
    }

    private boolean allUnique(String s, int start, int end) {
        Set<Character> characters = new HashSet<>();
        for (int i = start; i < end; i++) {
            if (characters.contains(s.charAt(i))) return false;
            characters.add(s.charAt(i));
        }
        return true;
    }

    /**
     * 滑动窗口，i,j都是窗口
     * @param s
     * @return
     */
    public int solve2(String s) {
        int n = s.length();
        Set<Character> set = new HashSet<>();
        int ans = 0, i = 0, j = 0;
        while (i < n && j < n) {
            if (!set.contains(s.charAt(j))) {
                set.add(s.charAt(j++));
                ans = Math.max(ans, j - i);
            }
            else {
                //移动窗口
                set.remove(s.charAt(i++));
            }
        }
        return ans;
    }


    /**
     * 优化的滑动窗口，i,j都是窗口
     * @param s
     * @return
     */
    public int solve3(String s) {
        int n = s.length(), ans = 0;
        Map<Character, Integer> map = new HashMap<>();
        for (int j = 0, i = 0; j < n; j++) {
            if (map.containsKey(s.charAt(j))) {
                //查找到重复元素，直接将i跳到重复元素的位置，移动i窗口
                i = Math.max(map.get(s.charAt(j)), i);
            }
            ans = Math.max(ans, j - i + 1);
            map.put(s.charAt(j), j + 1);
        }
        return ans;
    }



    public int noDuplicateStrLength(String str) {
        String[] split = str.split("");
        int size,i=0,j,k,max=0;
        size = str.length();
        for (j = 0; j < size; j++) {
            for (k = i; k < j; k++) {
                if (split[k].equals(split[j])) {
                    i = k + 1;
                    break;
                }
            }
            if (j-i+1 > max) {
                max = j-i+1;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        LengthOfLongestSubstring ofLongestSubstring = new LengthOfLongestSubstring();
//        int length = ofLongestSubstring.noDuplicateStrLength("abcde");
//        int length = ofLongestSubstring.solve1("asdfeasdf");
//        int length = ofLongestSubstring.solve2("ababcde");
        int length = ofLongestSubstring.solve3("ababcdeabcdef");
        System.out.println(length);

    }

}
