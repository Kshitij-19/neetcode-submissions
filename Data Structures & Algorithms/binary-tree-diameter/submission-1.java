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
    public int diameterOfBinaryTree(TreeNode root) {
        // if (root == null) return 0;

        // int maxDiameter = 0; // TWEAK 1: Global result variable
        // Map<TreeNode, Integer> heights = new HashMap<>(); // TWEAK 2: Map to store computed node heights

        // Deque<TreeNode> stack = new ArrayDeque<>();
        // TreeNode curr = root;
        // TreeNode lastVisited = null;

        // while (curr != null || !stack.isEmpty()) {
        //     // Push all left nodes to the stack
        //     while (curr != null) {
        //         stack.push(curr);
        //         curr = curr.left;
        //     }

        //     TreeNode peekNode = stack.peek();

        //     // If right child exists and hasn't been processed yet, traverse right child
        //     if (peekNode.right != null && lastVisited != peekNode.right) {
        //         curr = peekNode.right;
        //     } else {
        //         // Right child is null or already processed -> process current node
        //         TreeNode node = stack.pop(); // TWEAK 3: Pop node to process state
        //         lastVisited = node;

        //         // TWEAK 4: Retrieve child heights, update maxDiameter, and store node's height
        //         int leftHeight = heights.getOrDefault(node.left, 0);
        //         int rightHeight = heights.getOrDefault(node.right, 0);

        //         maxDiameter = Math.max(maxDiameter, leftHeight + rightHeight);
        //         heights.put(node, 1 + Math.max(leftHeight, rightHeight));
        //     }
        // }

        // return maxDiameter;



        Map<TreeNode, int[]> mp = new HashMap<>();
        mp.put(null, new int[]{0, 0});
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode node = stack.peek();

            if (node.left != null && !mp.containsKey(node.left)) {
                stack.push(node.left);
            } else if (node.right != null && !mp.containsKey(node.right)) {
                stack.push(node.right);
            } else {
                node = stack.pop();

                int[] leftData = mp.get(node.left);
                int[] rightData = mp.get(node.right);

                int leftHeight = leftData[0], leftDiameter = leftData[1];
                int rightHeight = rightData[0], rightDiameter = rightData[1];

                int height = 1 + Math.max(leftHeight, rightHeight);
                int diameter = Math.max(leftHeight + rightHeight,
                               Math.max(leftDiameter, rightDiameter));

                mp.put(node, new int[]{height, diameter});
            }
        }
        return mp.get(root)[1];
    }
}
