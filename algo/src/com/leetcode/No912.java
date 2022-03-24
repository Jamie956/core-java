package com.leetcode;

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
