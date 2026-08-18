/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */

class Solution {
    public void reorderList(ListNode head) {

        // if (head == null || head.next == null) return;

        // // find the middle
        // ListNode slow = head;
        // ListNode fast = head;
        // while(fast!=null && fast.next!=null) {
        //     fast = fast.next.next;
        //     slow = slow.next;
        // }

        // // reverse the remaining half
        // ListNode list2Head = slow;
        // ListNode curr = list2Head;
        // ListNode prev = null;
        // while(curr!=null) {
        //     ListNode temp = curr.next;
        //     curr.next = prev;
        //     prev = curr;
        //     curr = temp;
        // }

        // ListNode result = head;
        // ListNode curr1 = head;
        // ListNode curr2 = prev;
        // curr = head;
        // int count = 0;
        // while(curr1!=null && curr2!=null) {
        //     ListNode temp = curr.next;
        //     if(count%2==0) {
        //         curr.next = curr2;
        //         curr2 = curr2.next;
        //         curr1 = temp;
        //     } else {
        //         curr.next = curr1;
        //         curr1 = curr1.next;
        //         curr2 = temp;
        //     }
        //     curr = curr.next;
        //     count++;
        // }


        if (head == null || head.next == null) return;

        // 1. Find the middle (slow will point to middle)
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // 2. Reverse the second half starting from slow.next
        ListNode prev = null;
        ListNode curr = slow.next;
        slow.next = null; // Split the list into two halves

        while (curr != null) {
            ListNode nextTemp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextTemp;
        }

        // 3. Merge the two halves: head (first half) and prev (reversed second half)
        ListNode first = head;
        ListNode second = prev;

        while (second != null) {
            ListNode temp1 = first.next;
            ListNode temp2 = second.next;

            first.next = second;
            second.next = temp1;

            first = temp1;
            second = temp2;
        }

    
    }
}
