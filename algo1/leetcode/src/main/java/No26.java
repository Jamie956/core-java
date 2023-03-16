/*
26. Remove Duplicates from Sorted Array
Given a sorted array nums, remove the duplicates in-place such that each element appear only once and return the new length.
Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.
*/
public class No26 {
    /*
    移除有序数组的重复元素，输出移除后的数组长度，空间复杂度必须为O(1)
     */

    /**
     * 常规解法
     */
    public static int removeDuplicates(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int i = 0;
        int size = nums.length - 1;
        while (i < size) {
            if (nums[i] == nums[i + 1]) {
                for (int j = i + 1; j < nums.length - 1; j++) {
                    nums[j] = nums[j + 1];
                }
                size--;
            } else {
                i++;
            }
        }
        return i + 1;
    }

    /**
     * 快慢指针解法
     */
    public static int removeDuplicates2(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int slow = 0;
        int fast = 0;
        while (fast < nums.length - 1) {
            if (nums[fast] != nums[fast + 1]) {
                slow++;
                nums[slow] = nums[fast + 1];
            }
            fast++;
        }
        return slow + 1;
    }


    public static void main(String[] args) {
//        int[] nums = new int[]{1, 1, 2};
//        int[] nums = new int[]{1, 1};
//        int[] nums = new int[]{1};
        int[] nums = new int[]{};
//        int[] nums = new int[]{0,0,1,1,1,2,2,3,3,4};

//        System.out.println(removeDuplicates(nums));

        System.out.println(removeDuplicates2(nums));
    }
}
