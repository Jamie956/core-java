package com.leetcode;

/*
21. Merge Two Sorted Lists
Merge two sorted linked lists and return it as a new list. The new list should be made by splicing together the nodes of the first two lists.
*/
public class No21 {
    /*
    合并两个有序单链，返回一个新的有序单链
    stack
    3   mergeTwoLists(null, 8) -> 8
    1   mergeTwoLists(7, 8) -> 7 8
    1   mergeTwoLists(5->7, 8) -> 5 7 8
    2   mergeTwoLists(5->7, 4->8) -> 4 5 7 8
    2   mergeTwoLists(5->7, 2->4->8) -> 2 4 5 7 8
    1   mergeTwoLists(1->5->7, 2->4->8) -> 1 2 4 5 7 8
     */

    static class ListNode {
        int val;
        ListNode next;

        public ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list1 == null) {
            return list2;
        } else if (list2 == null) {
            return list1;
        } else if (list1.val < list2.val) {
            list1.next = mergeTwoLists(list1.next, list2);
            return list1;
        } else {
            list2.next = mergeTwoLists(list1, list2.next);
            return list2;
        }
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(1, new ListNode(5, new ListNode(7, null)));
        ListNode l2 = new ListNode(2, new ListNode(4, new ListNode(8, null)));

        ListNode l = mergeTwoLists(l1, l2);
        System.out.println(l);
    }

}
