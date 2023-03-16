/*
160. Intersection of Two Linked Lists
Given the heads of two singly linked-lists headA and headB, return the node at which the two lists intersect. If the two linked lists have no intersection at all, return null.

Example 1:
Input: intersectVal = 8, listA = [4,1,8,4,5], listB = [5,6,1,8,4,5], skipA = 2, skipB = 3
Output: Intersected at '8'
Explanation: The intersected node's value is 8 (note that this must not be 0 if the two lists intersect).
From the head of A, it reads as [4,1,8,4,5]. From the head of B, it reads as [5,6,1,8,4,5]. There are 2 nodes before the intersected node in A; There are 3 nodes before the intersected node in B.

读题：找出两条链相交的元素
*/
public class No160 {
    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        ListNode c1 = headA;
        ListNode c2 = headB;
        //使2个指针处于同一起跑线
        while (c1 != c2) {
            c1 = (c1 == null) ? headB : c1.next;
            c2 = (c2 == null) ? headA : c2.next;
        }
        return c1;
    }

    public static void main(String[] args) {
        ListNode n1 = new ListNode(4);
        ListNode n2 = new ListNode(1);

        ListNode k1 = new ListNode(5);
        ListNode k2 = new ListNode(6);
        ListNode k3 = new ListNode(1);

        ListNode m1 = new ListNode(8);
        ListNode m2 = new ListNode(4);
        ListNode m3 = new ListNode(5);

        m1.next = m2;
        m2.next = m3;

        n1.next = n2;
        n2.next = m1;

        k1.next = k2;
        k2.next = k3;
        k3.next = m1;

        ListNode r = getIntersectionNode(n1, k1);
        System.out.println();
    }

}
