/*
38. Count and Say
Given an integer n where 1 ≤ n ≤ 30, generate the n th term of the count-and-say sequence.

1.     1
2.     11
3.     21
4.     1211
5.     111221

n=5
the 1 th row must be 1
2 of row generate 11 from 1, mean that count+number
3 of row, 11->21, read the first number 1,
  recording count 1, then reading next number 1, it alse is 1, account++
  account=2, number=1, result->21
 */
public class No38 {
    /*
    统计字符串每个字符的个数
    */
    public static String countAndSay(int n) {
        String seq = "1";
        for (int i = 0; i < n-1; i++) {
            seq = countAndSayBySeq(seq);
        }
        return seq;
    }

    public static String countAndSayBySeq(String sequence) {
        String prev = "";
        int count = 0;

        StringBuilder nextSeq = new StringBuilder();
        for (int i = 0; i < sequence.length(); i++) {
            String s = String.valueOf(sequence.charAt(i));
            if(prev.length()==0) {
                prev = s;
                count = 1;
            }
            else if (s.equals(prev)) {
                count++;
            }
            else {
                nextSeq.append(count).append(prev);
                prev = s;
                count = 1;
            }
            if (i+1 == sequence.length()) {
                nextSeq.append(count).append(prev);
            }
        }
        return nextSeq.toString();
    }

    public static void main(String[] args) {
        System.out.println(countAndSay(6));
    }
}
