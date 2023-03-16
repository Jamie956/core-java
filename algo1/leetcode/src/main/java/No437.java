public class No437 {
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

    public static int pathSum(TreeNode root, int targetSum) {
        if (root == null) return 0;
        return paths(root, targetSum) + pathSum(root.left, targetSum) + pathSum(root.right, targetSum);
    }

    public static int paths(TreeNode root, int targetSum) {
        if (root == null) return 0;
        int count = 0;
        if (root.val == targetSum) {
            count += 1;
        }
        return count + paths(root.left, targetSum - root.val)
                + paths(root.right, targetSum - root.val);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.right = new TreeNode(-3);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(2);
        root.left.left.left = new TreeNode(3);
        root.left.left.right = new TreeNode(-2);
        root.left.right.right = new TreeNode(1);
        root.right.right = new TreeNode(11);

        int i = pathSum(root, 8);
    }
}
