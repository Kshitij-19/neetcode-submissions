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

    private static class Pair<K,V> {
        private final K key;
        private final V value;

        public Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }
        public K getKey() {return this.key;}
        public V getValue() {return this.value;}
    }

    public List<Integer> rightSideView(TreeNode root) {

        if(root == null) return new ArrayList<>();

        HashSet<Integer> set = new HashSet<>();
        ArrayList<Integer> res = new ArrayList<>();

        // dfs(root, set, res, 0);

        iterative_dfs(root, set, res);

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

    public void iterative_dfs(TreeNode root, HashSet<Integer> set, ArrayList<Integer> res) {
        Deque<Pair<TreeNode, Integer>> stack = new ArrayDeque<>();
        stack.push(new Pair<>(root,1));
        while(!stack.isEmpty()) {
            Pair<TreeNode, Integer> node = stack.pop();
            int depth = node.getValue();
            if(!set.contains(depth)) {
                res.add(node.getKey().val);
                set.add(depth);
            }
            if(node.getKey().left!=null) stack.push(new Pair<>(node.getKey().left, depth + 1));
            if(node.getKey().right!=null) stack.push(new Pair<>(node.getKey().right, depth + 1));
        }
    }
}
