package com.leetcode;

/*
234. Palindrome Linked List
Given the head of a singly linked list, return true if it is a palindrome.

Example 1:
Input: head = [1,2,2,1]
Output: true

Example 2:
Input: head = [1,2]
Output: false

Constraints:
The number of nodes in the list is in the range [1, 105].
0 <= Node.val <= 9

Follow up: Could you do it in O(n) time and O(1) space?

读题：输入一个单链，判断是否是回文

解题思路：快慢指针将链表对半分开，并且左边的链表做反正处理，比较两条链表是否一致，一致则是回文
 */
public class No234 {
    private static class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }
    }

    public static boolean isPalindrome(ListNode head) {
        //一个元素也算回文
        if (head.next == null) {
            return true;
        }
        //快慢指针
        ListNode fast = head;
        ListNode slow = head;
        ListNode reverseList = null;
        ListNode prevSlowNode = null;
        while (fast != null && fast.next != null) {
            reverseList = slow;
            slow = slow.next;
            //快指针跳跃
            fast = fast.next.next;
            //头插反转链表
            reverseList.next = prevSlowNode;
            prevSlowNode = reverseList;
        }

        //数组长度为奇数
        if (fast != null) {
            slow = slow.next;
        }

        while (reverseList != null && slow != null) {
            if (slow.val != reverseList.val) {
                return false;
            }
            reverseList = reverseList.next;
            slow = slow.next;
        }
        return true;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(2);
        head.next.next.next = new ListNode(1);

        System.out.println(isPalindrome(head));
    }
}