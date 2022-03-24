package com.leetcode;

public class No283 {
    public static void moveZeroes(int[] nums) {
        int zeroCount = 0;
        int i=0;
        for(i=0;i<nums.length;i++){
            if(nums[i]==0){
                zeroCount++;
            }else{
                nums[i-zeroCount] = nums[i];
            }
        }

        if((i-zeroCount)<nums.length){
            i = i-zeroCount;
            while(i<nums.length){
                nums[i] = 0;
                i++;
            }
        }
    }

    public static void moveZeroes2(int[] nums) {
        int i=0;
        int j=0;

        for(i=0;i<nums.length;i++){
            if(nums[i] != 0){
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
                j++;
            }
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[]{0,1,0,3,12};
        moveZeroes2(nums);
        for (int num : nums) {
            System.out.println(num);
        }
    }
}
