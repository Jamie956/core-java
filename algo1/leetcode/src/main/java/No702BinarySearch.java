import junit.framework.Assert;

public class No702BinarySearch {
    public static void main(String[] args) {
        Solution solution = new Solution();
        Assert.assertEquals(4, solution.search(new int[]{-1, 0, 3, 5, 9, 12}, 9));
        Assert.assertEquals(-1, solution.search(new int[]{-1,0,3,5,9,12}, 2));
        Assert.assertEquals(0, solution.search(new int[]{5}, 5));
    }

    static class Solution {
        public int search(int[] nums, int target) {
            int left = 0;
            int right = nums.length - 1;
            while (left <= right) {
                int mid = (right - left) / 2 + left;
                int num = nums[mid];

                if (num == target) {
                    return mid;
                }
                if (num < target) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
            return -1;
        }
    }
}
