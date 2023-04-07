import java.util.ArrayList;
import java.util.List;

public class No78Subsets {
    public static void main(String[] args) {
//        Solution solution = new Solution();
//        List<List<Integer>> res = solution.subsets(new int[]{1, 2, 3});
//        System.out.println(res);

        Solution2 solution = new Solution2();
        List<List<Integer>> res = solution.subsets(new int[]{1, 2, 3});
        System.out.println(res);
    }

    static class Solution {
        List<Integer> tmp = new ArrayList<Integer>();
        List<List<Integer>> result = new ArrayList<List<Integer>>();

        public List<List<Integer>> subsets(int[] nums) {
            dfs(0, nums);
            return result;
        }

        public void dfs(int cur, int[] nums) {
            if (cur == nums.length) {
                result.add(new ArrayList<Integer>(tmp));
                return;
            }
            tmp.add(nums[cur]);
            dfs(cur + 1, nums);
            tmp.remove(tmp.size() - 1);
            dfs(cur + 1, nums);
        }
    }

    static class Solution2 {
        List<List<Integer>> result = new ArrayList<List<Integer>>();

        public List<List<Integer>> subsets(int[] nums) {
            for (int k = 0; k <= nums.length; k++) {
                backTrace(0, k, new ArrayList<>(), nums);
            }
            return result;
        }

        // k 限定的长度
        public void backTrace(int start, int k, ArrayList<Integer> tmp, int[] nums) {
            if (k == 0) {
                result.add(new ArrayList<>(tmp));
                return;
            }
            for (int i = start; i < nums.length; i++) {
                tmp.add(nums[i]);
                backTrace(i + 1, k - 1, tmp, nums);
                tmp.remove(tmp.size() - 1);
            }
        }
    }
}
