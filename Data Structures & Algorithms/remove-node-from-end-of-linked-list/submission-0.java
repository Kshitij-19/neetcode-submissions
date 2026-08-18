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
    public ListNode removeNthFromEnd(ListNode head, int n) {
        int count = 1;
        ListNode node = head;
        while(count!=n && node!=null) {
            node = node.next;
            count++;
        }
        if(node==null) return head;

        // ListNode node2 = head;
        // node = node.next;
        // while(node!=null && node.next!=null) {
        //     node2 = node2.next;
        //     node = node.next;
        // }
        // ListNode lastNode;
        // if(node2.next == null) lastNode = null;
        // else lastNode = node2.next.next;
        // node2.next = lastNode;

        ListNode head2 = new ListNode(0);
        head2.next = head;
        ListNode node2 = head2;
        //node = node.next;
        while(node!=null && node.next!=null) {
            node2 = node2.next;
            node = node.next;
        }
        
        node2.next = node2.next.next;
        return head2.next;
    }
}
