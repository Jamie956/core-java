import org.junit.Assert;

import java.util.HashMap;
import java.util.Map;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode() {}
 * ListNode(int val) { this.val = val; }
 * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */

public class LC19 {
    public static void main(String[] args) {
        test1();
        test2();
        test3();
        test4();
        test5();
    }

    public static void test1() {
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(3);
        ListNode n4 = new ListNode(4);
        ListNode n5 = new ListNode(5);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;

        LC19 lc19 = new LC19();
        ListNode answer = lc19.removeNthFromEnd(n1, 2);

        Assert.assertEquals(answer.val, 1);
        Assert.assertEquals(answer.next.val, 2);
        Assert.assertEquals(answer.next.next.val, 3);
        Assert.assertEquals(answer.next.next.next.val, 5);
    }

    public static void test2() {
        ListNode n1 = new ListNode(1);

        LC19 lc19 = new LC19();
        ListNode answer = lc19.removeNthFromEnd(n1, 1);

        Assert.assertNull(answer);
    }

    public static void test3() {
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(3);
        n1.next = n2;
        n2.next = n3;

        LC19 lc19 = new LC19();
        ListNode answer = lc19.removeNthFromEnd(n1, 3);

        Assert.assertEquals(answer.val, 2);
        Assert.assertEquals(answer.next.val, 3);
    }

    public static void test4() {
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        n1.next = n2;

        LC19 lc19 = new LC19();
        ListNode answer = lc19.removeNthFromEnd(n1, 1);

        Assert.assertEquals(answer.val, 1);
    }

    public static void test5() {
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        n1.next = n2;

        LC19 lc19 = new LC19();
        ListNode answer = lc19.removeNthFromEnd(n1, 2);

        Assert.assertEquals(answer.val, 2);
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode node = head;
        int length = 0;
        Map<Integer, ListNode> map = new HashMap<>();
        while (node != null) {
            length++;
            map.put(length, node);
            node = node.next;
        }

        if (length == 1 && n == 1) {
            return null;
        }

        int rmNext = length - n;
        if (rmNext == 0) {
            head = head.next;
            return head;
        }

        ListNode rmNextNode = map.get(rmNext);

        if (rmNextNode != null) {
            rmNextNode.next = rmNextNode.next.next;
        }
        return head;
    }
}

