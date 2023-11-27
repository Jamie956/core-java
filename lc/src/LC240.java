import org.junit.Assert;
import org.junit.Test;

public class LC240 {
    @Test
    public void test1() {
        int[][] matrix = new int[][]{{1, 4, 7, 11, 15}, {2, 5, 8, 12, 19}, {3, 6, 9, 16, 22}, {10, 13, 14, 17, 24}, {18, 21, 23, 26, 30}};
        Assert.assertTrue(new LC240().searchMatrix(matrix, 5));
    }

    public boolean searchMatrix(int[][] matrix, int target) {
        for (int i = 0; i < matrix.length; i++) {
            int low = 0;
            int high = matrix[0].length - 1;
            // dichotomy
            while (low <= high) {
                int mid = (high - low) / 2 + low;
                int v = matrix[i][mid];
                if (v == target) {
                    return true;
                }
                if (v < target) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }
        }
        return false;
    }

}
