package com.lyoyang.offfer;

import java.util.Arrays;

/**
 * 输入某二叉树的前序遍历和中序遍历的结果， 请重建出该二叉树。 假
 * 设输入的前序遍历和中序遍历的结果中都不含重复的数字。 例如输入前序遍历序列
 * {1,2,4,7,3,5,6,8}和中序遍历序列{4,7,2,1,5,3,8,6}， 则重建二叉树并返回
 * 先找出根节点， 然后利用递归方法构造二叉树
 */
public class Solution4 {

    public static void main(String[] args) {
        int[] arrs = new int[]{1,2,3,4,5};
        int[] ints = Arrays.copyOfRange(arrs, 1, 3);
        Arrays.stream(ints).forEach(System.out::println);
    }

    public TreeNode reConstructBinaryTree(int[] pre, int[] in) {
        if (pre == null || pre.length == 0 || in == null || in.length == 0) {
            return null;
        }
        if (pre.length != in.length) {
            return null;
        }
        TreeNode root = new TreeNode(pre[0]);
        for (int i = 0; i < pre.length; i++) {
            if (pre[0] == in[i]) {
                root.left = reConstructBinaryTree(Arrays.copyOfRange(pre, 1, i + 1),
                        Arrays.copyOfRange(in, 0, i));
                root.right = reConstructBinaryTree(Arrays.copyOfRange(pre, i + 1, pre
                .length), Arrays.copyOfRange(in, i + 1, in.length));
            }
        }
        return root;
    }


    public static TreeNode reConstructBinaryTree2(int[] pre, int[] in) {
        if (pre == null || pre.length == 0 || in == null || in.length == 0) {
            return null;
        }
        return healper(pre, 0, pre.length - 1, in, 0, in.length - 1);
    }

    private static TreeNode healper(int[] pre, int preL, int preR, int[] in, int inL, int inR) {
        if (preL > preR || inL > inR) {
            return null;
        }

        int rootVal = pre[preL];
        int index = 0;

        while (index <= inR && in[index] != rootVal) {
            index++;
        }
        TreeNode root = new TreeNode(rootVal);
        root.left = healper(pre, preL + 1, preL - inL + index,
                in, inL, index);
        root.right = healper(pre, preL - inL + index + 1, preR, in, index + 1, inR);

        return root;
    }


}
