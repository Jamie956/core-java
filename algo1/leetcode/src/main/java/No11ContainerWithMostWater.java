import junit.framework.Assert;

public class No11ContainerWithMostWater {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int result = solution.maxArea(new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7});
        Assert.assertEquals(49, result);
    }

    // 双指针解法
    static class Solution {
        public int maxArea(int[] height) {
            int area = 0;
            int l = 0;
            int r = height.length-1;
            while (l<r) {
                area = Math.max(area, (r-l)*Math.min(height[l], height[r]));
                // 移动高度短的指针
                if (height[l] < height[r]) {
                    l++;
                } else {
                    r--;
                }
            }
            return area;
        }
    }
}
