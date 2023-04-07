//https://leetcode-cn.com/problems/excel-sheet-column-number/submissions/
public class No171 {
    public static int titleToNumber(String columnTitle) {
        int len = columnTitle.length();
        int ret = 0;
        for (int i = 0; i < len; i++) {
            //26进制，每一个高位都是26的倍数
            ret += (columnTitle.charAt(i) - 'A' + 1) * Math.pow(26, len - 1 - i);
        }
        return ret;
    }

    public static void main(String[] args) {
        titleToNumber("AB");
    }
}
