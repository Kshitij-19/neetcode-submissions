/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */

class Solution {
    public int kthSmallest(TreeNode root, int k) {
        
        // modifications in iterative inorder traversal generic code

        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode curr = root;

        int res = 0;

        while (curr != null || !stack.isEmpty()) {
            // Step 1: Drill down the left spine, pushing all ancestor nodes
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }

            // Step 2: Pop leftmost unvisited node and process
            curr = stack.pop();
            // if(k==0) return res;

            k--;
            if (k==0) return curr.val;

            // Step 3: Shift focus to right subtree
            curr = curr.right;
        }

        return res;
    }
}
