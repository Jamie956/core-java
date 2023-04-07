import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
118. Pascal's Triangle
Given an integer numRows, return the first numRows of Pascal's triangle.

In Pascal's triangle, each number is the sum of the two numbers directly above it as shown:

Example 1:
Input: numRows = 5
Output: [[1],[1,1],[1,2,1],[1,3,3,1],[1,4,6,4,1]]

Example 2:
Input: numRows = 1
Output: [[1]]

Constraints:
1 <= numRows <= 30

读题：输入一个整数的行号，返回杨辉三角对应行号的值

解题思路：
1
1 1
1 2 1
1 3 3 1
1 4 6 4 1
1 5 10 10 5 1

例子：121的下一行
前后加0，再相加
1210
0121
0+1,1+2,2+1,1+0

numRows=1, []
numRows=2, [1]
numRows=3, [[1], [1,1]]
numRows=4, [[1], [1,1], [1,2,1]]
numRows=6, [[1], [1,1], [1,2,1], [1,3,3,1]]
*/
public class No118 {
    public static List<List<Integer>> generate(int numRows) {
        List<List<Integer>> rows = new ArrayList<>(numRows - 1);
        rows.add(Collections.singletonList(1));
        for (int i = 0; i < numRows - 1; i++) {
            List<Integer> prevRow = rows.get(i);
            List<Integer> row = new ArrayList<>(prevRow.size() + 1);
            for (int j = 0; j < prevRow.size() + 1; j++) {
                int x = (j < prevRow.size()) ? prevRow.get(j) : 0;
                int y = (j - 1 < 0) ? 0 : prevRow.get(j - 1);
                row.add(x + y);
            }
            rows.add(row);
        }
        return rows;
    }

    public static void main(String[] args) {
        System.out.println(generate(5));
    }
}
