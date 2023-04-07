import java.util.ArrayList;
import java.util.List;

public class No39CombinationSum {
    public static void main(String[] args) {
        Solution solution = new Solution();
        List<List<Integer>> lists = solution.combinationSum(new int[]{2, 3, 5}, 8);
        System.out.println(lists);
    }

    static class Solution {
        public List<List<Integer>> combinationSum(int[] candidates, int target) {
            List<List<Integer>> ans = new ArrayList<>();
            List<Integer> combine = new ArrayList<>();
            dfs(candidates, target, ans, combine, 0);
            return ans;
        }

        public void dfs(int[] candidates, int target, List<List<Integer>> ans, List<Integer> combine, int idx) {
            if (idx == candidates.length) {
                return;
            }
            if (target == 0) {
                ans.add(new ArrayList<>(combine));
                return;
            }
            // 递归到树子叶时回溯
            dfs(candidates, target, ans, combine, idx + 1);

            if (target - candidates[idx] >= 0) {
                combine.add(candidates[idx]);
                // 直到递归出 target==0
                dfs(candidates, target - candidates[idx], ans, combine, idx);
                combine.remove(combine.size() - 1);
            }
        }
    }
}
