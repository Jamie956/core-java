//https://leetcode-cn.com/problems/shortest-unsorted-continuous-subarray/
public class No581 {

    public static int findUnsortedSubarray(int[] nums) {
        int l = 0, r = 0, max = nums[0], min = nums[nums.length - 1];

        for (int i = 0; i < nums.length; i++) {
            int end = nums.length - 1 - i;
            //从右到左比较，如果读到的数比右边的最大值大，更新左边界指针
            if (nums[end] <= min) {
                min = nums[end];
            } else {
                l = end;
            }
            if (max <= nums[i]) {
                max = nums[i];
            } else {
                r = i;
            }
        }
        return r > l ? r - l + 1 : 0;
    }

    public static void main(String[] args) {
        int i = findUnsortedSubarray(new int[]{2, 6, 4, 8, 10, 9, 15});
    }
}
