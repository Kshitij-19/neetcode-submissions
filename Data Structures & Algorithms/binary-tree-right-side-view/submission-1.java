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
    public List<Integer> rightSideView(TreeNode root) {

        if(root == null) return new ArrayList<>();

        HashSet<Integer> set = new HashSet<>();
        ArrayList<Integer> res = new ArrayList<>();

        dfs(root, set, res, 0);

        return res;

    }
    
    public void dfs(TreeNode root, HashSet<Integer> set, ArrayList<Integer> res, int depth) {

        if(root == null) return;

        if(!set.contains(depth)) {
            res.add(root.val);
            set.add(depth);
        }
        dfs(root.right, set, res, depth+1);
        dfs(root.left, set, res, depth+1);
    }
}
