package com.leetcode;

import junit.framework.Assert;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 二叉树的前序遍历：按照访问根节点——左子树——右子树的方式遍历这棵树
 */
public class No144PreorderTraversal {
    public static void main(String[] args) {
        TreeNode tree1 = new TreeNode(1);
        tree1.setLeft(null);
        TreeNode leaf1 = new TreeNode(2);
        leaf1.setLeft(new TreeNode(3));
        tree1.setRight(leaf1);

        Solution solution = new Solution();
        List<Integer> res = solution.preorderTraversal(tree1);
        Assert.assertEquals(1, res.get(0).intValue());
        Assert.assertEquals(2, res.get(1).intValue());
        Assert.assertEquals(3, res.get(2).intValue());

        Solution2 solution2 = new Solution2();
        List<Integer> res2 = solution2.preorderTraversal(tree1);
        Assert.assertEquals(1, res2.get(0).intValue());
        Assert.assertEquals(2, res2.get(1).intValue());
        Assert.assertEquals(3, res2.get(2).intValue());
    }

    static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int x) {
            val = x;
        }

        public int getVal() {
            return val;
        }

        public void setVal(int val) {
            this.val = val;
        }

        public TreeNode getLeft() {
            return left;
        }

        public void setLeft(TreeNode left) {
            this.left = left;
        }

        public TreeNode getRight() {
            return right;
        }

        public void setRight(TreeNode right) {
            this.right = right;
        }
    }

    static class Solution {
        public List<Integer> preorderTraversal(TreeNode root) {
            List<Integer> res = new ArrayList<>();
            preorder(root, res);
            return res;
        }

        private void preorder(TreeNode node, List<Integer> res) {
            if (node == null) {
                return;
            }

            int val = node.val;
            TreeNode left = node.left;
            TreeNode right = node.right;
            res.add(val);
            preorder(left, res);
            preorder(right, res);
        }
    }

    static class Solution2 {
        public List<Integer> preorderTraversal(TreeNode root) {
            List<Integer> res = new ArrayList<>();
            if (root == null) {
                return res;
            }

            Deque<TreeNode> stack = new LinkedList<>();
            // 作为树指针
            TreeNode node = root;
            while (!stack.isEmpty() || node != null) {
                // 树指针一直往左树移动，直到末尾
                while (node != null) {
                    res.add(node.val);
                    // 父节点入栈，用来出栈遍历右树
                    stack.push(node);
                    node = node.left;
                }
                node = stack.pop();
                // 从栈读取父节点的右树
                node = node.right;
            }
            return res;
        }
    }

}
