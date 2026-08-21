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
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        // with just preorder array we cannot generate tree because its not a complete binary tree
        //if it was complete binary tree then we would have generated it just with preorder array

        Map<Integer, Integer> inorderIndexMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inorderIndexMap.put(inorder[i], i);
        }
        
        // Pass pre_idx wrapper or bounds instead of using instance state
        return buildTreeHelper(preorder, 0, 0, inorder.length - 1, inorderIndexMap);
    }

    private TreeNode buildTreeHelper(
            int[] preorder, 
            int preStart, 
            int inStart, 
            int inEnd, 
            Map<Integer, Integer> inorderIndexMap) {

        if (preStart > preorder.length - 1 || inStart > inEnd) {
            return null;
        }

        int rootVal = preorder[preStart];
        TreeNode root = new TreeNode(rootVal);
        int inIndex = inorderIndexMap.get(rootVal);
        int leftTreeSize = inIndex - inStart;

        // Calculate next preorder indices deterministically instead of mutating a global pointer
        root.left = buildTreeHelper(preorder, preStart + 1, inStart, inIndex - 1, inorderIndexMap);
        root.right = buildTreeHelper(preorder, preStart + leftTreeSize + 1, inIndex + 1, inEnd, inorderIndexMap);

        return root;
    }
}


