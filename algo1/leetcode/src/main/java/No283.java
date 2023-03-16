/*
读题：给出一个整数数组，将数组里的0移动到数组末尾，不允许复制数组，要在原数组上修改

解题思路：

●  定义指针i遍历数组，当i读到0时，记录0的个数，当读到非0的整数时移动元素arr[i-count]=arr[i]，最后把末尾的0补齐
●  双指针划分区间，分别划分为不是0区间、0的区间和还没读取的区间；j指针在左，i指针在右；i指针移动遍历，当读取到0时移动i指针，当读取到非0时，i,j元素发生交换，移动j指针。
总结：元素移动 -> 指针，元素交换
 */
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
