package com.leetcode;

import java.util.ArrayList;
import java.util.List;

/*
94. Binary Tree Inorder Traversal
Given the root of a binary tree, return the inorder traversal of its nodes' values.

Example 1:
Input: root = [1,null,2,3]
Output: [1,3,2]

Example 2:
Input: root = []
Output: []

Example 3:
Input: root = [1]
Output: [1]

Constraints:
The number of nodes in the tree is in the range [0, 100].
-100 <= Node.val <= 100

中序遍历（inorder traversal，LDR，中根遍历）：左根右
 */
public class No94 {
    private static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int x) {
            val = x;
        }
    }

    static List<Integer> resultSet = new ArrayList<>();
    public static List<Integer> inorderTraversal(TreeNode root) {
        recursion(root);
        return resultSet;
    }

    public static void recursion(TreeNode root) {
        if(root == null) {
            return;
        }
        //一个节点没有左节点时，将节点值加入结果集，并递归右结点
        if(root.left == null) {
            resultSet.add(root.val);
            recursion(root.right);
        } else {
            //如果一个结点有左结点，递归左结点，递归后将结点值加到结果集，递归右结点
            recursion(root.left);
            resultSet.add(root.val);
            recursion(root.right);
        }
    }

    public static void main(String[] args) {
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        n1.right = n2;
        n2.left = n3;

        List<Integer> ret = inorderTraversal(n1);
        System.out.println();
    }
}
