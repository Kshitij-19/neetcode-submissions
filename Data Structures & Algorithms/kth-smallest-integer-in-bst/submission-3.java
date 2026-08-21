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
        
        // Approach 1
        // return iterativeInorder(root,k);

        // Approach 2
        return morrisTraversal(root, k);
    }

    public int morrisTraversal(TreeNode root, int k) {

        TreeNode curr = root;

        while(curr!=null) {

            // 1. Check if there is no left subtree so we can just print the root
            if(curr.left==null) {
                // print and move right
                k--;
                if(k==0) return curr.val;
                curr = curr.right;
            } else {
                // 2. Current node has the left child, so find the predecessor in left tree
                TreeNode predecessor = curr.left;

                while(predecessor.right!=null && predecessor.right!=curr) {
                    predecessor = predecessor.right;
                }

                // 3. If we don't have the predecessor set until now
                if(predecessor.right==null) {
                    predecessor.right = curr;
                    curr = curr.left; // Now we have successfully stored the current node through predecessor, so we can leave the current node comeback later through predecessor

                } else { // 4. Here (predecessor.right==curr) and so we already had predecessor of curr node and we already have traversed the curr node's entire left sub tree, so delete the predecessor link and visit the curr node
                    predecessor.right = null;

                    k--;
                    if(k==0) return curr.val;
                    curr = curr.right;
                }
            }
        }
        return -1;

    }

    public int iterativeInorder(TreeNode root, int k) {
        // modifications in iterative inorder traversal generic code

        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode curr = root;

        //int res = 0;

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

        //return res;
        return -1;
    }
}
