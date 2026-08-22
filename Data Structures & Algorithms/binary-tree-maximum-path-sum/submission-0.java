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
    public int maxPathSum(TreeNode root) {

        if(root==null) return 0;

        int[] max = new int[1];
        max[0] = Integer.MIN_VALUE;
        int temp = dfs(root, max);
        return max[0];
    }

    public int dfs(TreeNode root, int [] max) {
        // if (root == null) return 0; // cannot do this, null should be checked before calling

        long left = Integer.MIN_VALUE;
        if (root.left!=null) left = dfs(root.left, max);
        long right = Integer.MIN_VALUE;
        if (root.right!=null) right = dfs(root.right, max);
        long curr = root.val;
        long sum = 0;
        long path = 0;

        // check if we wanna add left or right 
        if (left>right) {
            if (left+curr > curr) sum = left+curr;
            else sum = curr;
            path = Math.max(sum, sum+right);
        } else {
            if (right+curr > curr) sum = right+curr;
            else sum = curr;
            path = Math.max(sum, sum+left);
        }

        // check if current node is root of maximum path sum
        
        // max[0] = Math.max(max[0], (int) Math.max(sum, left+right+curr));
        max[0] = Math.max(max[0], (int) path);

        // System.out.println("Node " + curr + " max " + max[0] + " sum " + sum);
        return (int) sum;
    }
}
