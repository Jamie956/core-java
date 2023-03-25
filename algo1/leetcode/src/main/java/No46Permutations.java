import java.util.ArrayList;
import java.util.List;

public class No46Permutations {
    public static void main(String[] args) {
        Solution solution = new Solution();
        List<List<Integer>> res = solution.permute(new int[]{1, 2, 3});
        System.out.println(res);
    }

    static class Solution {
        public List<List<Integer>> permute(int[] nums) {
            List<List<Integer>> res = new ArrayList<>();
            int[] visited = new int[nums.length];
            List<Integer> tmp = new ArrayList<>();
            backtrack(res, nums, tmp, visited);
            return res;
        }

        /*
           枚举每一种情况，对于读取过元素/经过的树路径使用 visited 记录
                 1
                / \\
               1  2 3
                 / \\
                 1 2 3
         */
        private void backtrack(List<List<Integer>> res, int[] nums, List<Integer> tmp, int[] visited) {
            if (tmp.size() == nums.length) {
                res.add(new ArrayList<>(tmp));
                return;
            }
            for (int i = 0; i < nums.length; i++) {
                // 排除已经走过的路径
                if (visited[i] != 1) {
                    visited[i] = 1;

                    // 记录当前节点
                    tmp.add(nums[i]);
                    // 继续往下个节点走
                    backtrack(res, nums, tmp, visited);

                    // 清除当前节点
                    visited[i] = 0;
                    tmp.remove(tmp.size() - 1);
                }
            }
        }
    }
}
