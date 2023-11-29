import org.junit.Assert;
import org.junit.Test;


public class LC24 {

    @Test
    public void test1() {
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(3);
        ListNode n4 = new ListNode(4);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;

        LC24 lc24 = new LC24();
        ListNode answer = lc24.swapPairs(n1);
        Assert.assertEquals(2, answer.val);
        Assert.assertEquals(1, answer.next.val);
        Assert.assertEquals(4, answer.next.next.val);
        Assert.assertEquals(3, answer.next.next.next.val);
    }

    @Test
    public void test2() {
        ListNode n1 = new ListNode(1);
        LC24 lc24 = new LC24();
        ListNode answer = lc24.swapPairs(n1);
        Assert.assertEquals(1, answer.val);
    }

    @Test
    public void test3() {
        LC24 lc24 = new LC24();
        ListNode answer = lc24.swapPairs(null);
        Assert.assertNull(answer);
    }

    public ListNode swapPairs(ListNode head) {
        if (head == null) {
            return null;
        }
        if (head.next == null) {
            return head;
        }
        ListNode answer = null;
        ListNode a = head;
        ListNode prefix = null;
        while (a != null && a.next != null) {
            ListNode b = a.next;
            ListNode suffix = a.next.next;

            a.next = suffix;
            b.next = a;
            if (prefix != null) {
                prefix.next = b;
            } else {
                answer = b;
            }

            prefix = a;
            a = suffix;
        }
        return answer;
    }

}
