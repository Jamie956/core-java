package com.leetcode;

/*
读题：整数数组升序排序

解题思路：

● 快速排序：找最后一个元素做支点，从左开始i指针遍历，判断小于支点的元素在左区间，
判断大于支点的元素放在中间区间，未遍历元素放在右区间，由j指针记录中区间左边界，再递归子区间。

 */
public class No912 {
    public static int[] sortArray(int[] nums) {
        quickSort(nums, 0, nums.length-1);
        return nums;
    }

    public static void quickSort(int[] nums, int l, int r) {
        if(l<r){
            int pivot = partition(nums, l, r);
            quickSort(nums, l, pivot-1);
            quickSort(nums, pivot+1, r);
        }
    }

    public static int partition(int[] nums, int l, int r) {
        int j = l;
        for (int i = l; i < r; i++) {
            if (nums[i]<nums[r]) {
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
                j++;
            }
        }
        int temp = nums[j];
        nums[j] = nums[r];
        nums[r] = temp;
        return j;
    }

    public static void main(String[] args) {
//        int[] nums = new int[]{3, 8, 1, 2, 5, 4};
//        int j = t.partition(nums, 0, 5);
//        for (int num : nums) {
//            System.out.println(num);
//        }
//        System.out.println("--");
//        System.out.println(j);


//        int[] nums = new int[]{3, 8, 1, 2, 5, 4};
        int[] nums = new int[]{5,1,1,2,0,0};
        sortArray(nums);
        for (int num : nums) {
            System.out.println(num);
        }
    }
}
