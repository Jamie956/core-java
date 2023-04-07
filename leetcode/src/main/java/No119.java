import java.util.ArrayList;
import java.util.List;

/*
119. Pascal's Triangle II
Given an integer rowIndex, return the rowIndexth (0-indexed) row of the Pascal's triangle.

In Pascal's triangle, each number is the sum of the two numbers directly above it as shown:

Example 1:
Input: rowIndex = 3
Output: [1,3,3,1]

Example 2:
Input: rowIndex = 0
Output: [1]

Example 3:
Input: rowIndex = 1
Output: [1,1]

Constraints:
0 <= rowIndex <= 33

Follow up: Could you optimize your algorithm to use only O(rowIndex) extra space?

1 3 3 1 0
0 1 3 3 1

0 1 3 3 1
1 3 3 1 0

1 4 6 4 1

解题思路：
求三角的第x行
数组补零，当前元素与下一个元素相加
*/
public class No119 {
    public static List<Integer> getRow(int rowIndex) {
        List<Integer> row = new ArrayList<>(rowIndex + 1);
        row.add(1);
        for (int i = 0; i < rowIndex; i++) {
            for (int j = i + 1; j >= 0; j--) {
                //末尾补0
                int x = j > i ? 0 : row.get(j);
                //开头补0
                int y = j - 1 < 0 ? 0 : row.get(j - 1);
                if (j <= i) {
                    row.remove(j);
                }
                row.add(j, x + y);
            }
        }
        return row;
    }

    public static void main(String[] args) {
        System.out.println(getRow(3));
    }
}
