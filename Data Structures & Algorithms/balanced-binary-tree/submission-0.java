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
    public boolean isBalanced(TreeNode root) {
        
        Deque<TreeNode> stack = new ArrayDeque<>();
        // stack.push(root); // we push root in second while loop
        TreeNode curr = root;
        TreeNode lastVisited = null;

        HashMap<TreeNode, Integer> map = new HashMap<>();
        // map.put(null,0); // better to use this map.getOrDefault(curr.left, 0);

        while(!stack.isEmpty() || curr!=null) {
            while(curr!=null) {
                stack.push(curr);
                curr = curr.left;
            }

            TreeNode peekNode = stack.peek();

            if(peekNode.right!=null && lastVisited!=peekNode.right) {
                curr = peekNode.right;
            } else {

                // please don't set curr = stack.pop();
                TreeNode parent = stack.pop();
                lastVisited = parent;

                int leftHeight = map.getOrDefault(parent.left, 0);
                int rightHeight = map.getOrDefault(parent.right, 0);

                if (Math.abs(leftHeight - rightHeight) > 1) return false;

                map.put(parent, 1 + Math.max(leftHeight, rightHeight));
            }
        }

        return true;
    }
}
