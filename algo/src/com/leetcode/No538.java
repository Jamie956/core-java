package com.leetcode;

//https://leetcode-cn.com/problems/convert-bst-to-greater-tree/
public class No538 {
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    private int sum = 0;

    public TreeNode convertBST(TreeNode root) {
        if (root == null) {
            return null;
        }
        convertBST(root.right);
        sum += root.val;
        root.val = sum;
        convertBST(root.left);
        return root;
    }

    public static void main(String[] args) {
        No538 t = new No538();

        TreeNode root = new TreeNode(0);
        root.right = new TreeNode(1);
        TreeNode treeNode = t.convertBST(root);
    }
}
