public class No48RotateImage {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] matrix = new int[][]{{1,2,3},{4,5,6},{7,8,9}};
        solution.rotate(matrix);
        System.out.println();
    }

    // matrix[col][n−row−1]=matrix[row][col]
    static class Solution {
        public void rotate(int[][] matrix) {
            int n = matrix.length;
            for (int i = 0; i < n / 2; ++i) {
                for (int j = 0; j < (n + 1) / 2; ++j) {
                    System.out.println("------ i="+i+" j="+j);
                    int temp = matrix[i][j];
                    System.out.println(i+","+j+" <- "+(n-j-1)+","+i);
                    matrix[i][j] = matrix[n - j - 1][i];
                    matrix[n - j - 1][i] = matrix[n - i - 1][n - j - 1];
                    matrix[n - i - 1][n - j - 1] = matrix[j][n - i - 1];
                    matrix[j][n - i - 1] = temp;
                }
            }
        }
    }
}
