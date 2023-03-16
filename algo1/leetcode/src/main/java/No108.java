/*
108. Convert Sorted Array to Binary Search Tree
Given an integer array nums where the elements are sorted in ascending order, convert it to a height-balanced binary search tree.
A height-balanced binary tree is a binary tree in which the depth of the two subtrees of every node never differs by more than one.

Example 1:
Input: nums = [-10,-3,0,5,9]
Output: [0,-3,9,-10,null,5]
Explanation: [0,-10,5,null,-3,null,9] is also accepted:

Example 2:
Input: nums = [1,3]
Output: [3,1]
Explanation: [1,3] and [3,1] are both a height-balanced BSTs.

Constraints:
1 <= nums.length <= 104
-104 <= nums[i] <= 104
nums is sorted in a strictly increasing order.

读题：输入一个整数升序数组，转化成一个高度平衡二叉树（每个节点两棵棵子树高度相差不超过1），输出平衡二叉树的数组

解题：输入的数组是有序的，那中间位置的元素就是根节点，并以此位界限，左边递归，右边递归
 */
public class No108 {
    private static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int x) {
            val = x;
        }
    }

    public static TreeNode sortedArrayToBST(int[] nums) {
        return recursion(nums, 0, nums.length - 1);
    }

    private static TreeNode recursion(int[] nums, int from, int to) {
        if (from > to) return null;
        int p = (from + to) / 2;
        TreeNode root = new TreeNode(nums[p]);
        root.left = recursion(nums, from, p - 1);
        root.right = recursion(nums, p + 1, to);
        return root;
    }

    public static void main(String[] args) {
        int[] arr = {-10, -3, 0, 5, 9};
        TreeNode treeNode = sortedArrayToBST(arr);
        System.out.println();
    }

}
