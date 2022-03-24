package com.leetcode;

/*
101. Symmetric Tree
Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).
*/
public class No101 {
    /*
        判断一棵树是否是对称
        比较左字树和右子树的根节点，递归子树的子树，叶子节点判断
     */
    static class TreeNode {
        private int val;
        private TreeNode left;
        private TreeNode right;

        public TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public static boolean isSymmetric(TreeNode root) {
        return isSymmetricRecursion(root, root);
    }

    private static boolean isSymmetricRecursion(TreeNode n1, TreeNode n2) {
        if ((n1 == null) && (n2 == null)) {
            return true;
        }
        if ((n1 == null) || (n2 == null)) {
            return false;
        }
        if (n1.val != n2.val) {
            return false;
        }
        return isSymmetricRecursion(n1.left, n2.right) && isSymmetricRecursion(n1.right, n2.left);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1,
                new TreeNode(2,
                        new TreeNode(3, null, null), new TreeNode(4, null, null)),
                new TreeNode(2,
                        new TreeNode(4, null, null), new TreeNode(3, null, null))
        );
        System.out.println(isSymmetric(root));
    }
}
